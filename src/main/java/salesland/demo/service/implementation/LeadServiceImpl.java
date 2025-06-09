package salesland.demo.service.implementation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import salesland.demo.persistence.entity.LeadDisociadoEntity;
import salesland.demo.persistence.entity.LeadTemporalEntity;
import salesland.demo.persistence.entity.LogCargaEntity;
import salesland.demo.persistence.entity.LogWebServiceEntity;
import salesland.demo.persistence.repository.CampoDisociarCampanaRepository;
import salesland.demo.persistence.repository.CampoDisociarRepository;
import salesland.demo.persistence.repository.LeadDisociadoRepository;
import salesland.demo.persistence.repository.LeadTemporalRepository;
import salesland.demo.persistence.repository.LogCargaRepository;
import salesland.demo.persistence.repository.LogWebServiceRepository;
import salesland.demo.presentation.dto.LeadDisociadoDto;
import salesland.demo.presentation.dto.LeadRequestDto;
import salesland.demo.presentation.dto.LeadResponseDto;
import salesland.demo.service.exception.ApiStatusException;
import salesland.demo.service.exception.BusinessException;
import salesland.demo.service.exception.ResourceNotFoundException;
import salesland.demo.service.http.LeadClient;
import salesland.demo.service.interfaces.LeadService;
import salesland.demo.util.JsonUtil;
import salesland.demo.util.LeadDisociadoMapper;
import salesland.demo.util.LeadMapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService {
    @Autowired
    private final LeadTemporalRepository leadTemporalRepository;
    @Autowired
    private final LeadDisociadoRepository leadDisociadoRepository;
    @Autowired
    private final CampoDisociarRepository auxDisociarRepository;
    @Autowired
    private final CampoDisociarCampanaRepository auxCampanaDisociarRepository;
    @Autowired
    private final LogWebServiceRepository logWsRepository;
    @Autowired
    private final LogCargaRepository logsCargaRepository;
    @Autowired
    private final LeadClient leadClient; // HTTP API real
    private final Validator validator;

    // 1. Registro de lead (entrypoint desde controller)
    @Override
    public LeadResponseDto registrarLead(LeadRequestDto dto, boolean usaApi) {
        validarDto(dto);

        if (usaApi) {
            try {
                ResponseEntity<LeadResponseDto> apiResponse = leadClient.fromApi(dto);

                if (apiResponse.getBody() == null || !esRespuestaOk(apiResponse.getBody())) {
                    //REGISTRAMOS EL LOG AQUI
                    registrarLogError(
                            "API",
                            apiResponse.getBody() != null ? apiResponse.getBody().getResultado() : "Respuesta vacía de API",
                            toJson(dto)
                    );
                    throw new ApiStatusException(
                            HttpStatus.valueOf(apiResponse.getStatusCode().value()),
                            apiResponse.getBody()
                    );
                }
                guardarYProcesar(dto);
                return apiResponse.getBody();
            } catch (HttpStatusCodeException ex) {
                registrarLogError(
                        "API",
                        ex.getResponseBodyAsString(),
                        toJson(dto)
                );
                throw new ApiStatusException(
                        HttpStatus.valueOf(ex.getStatusCode().value()),
                        ex.getResponseBodyAsString()
                );
            }
        }

        //flujo local normal
        LeadTemporalEntity entity = LeadMapper.toEntity(dto);
        leadTemporalRepository.save(entity);
        procesarAltaLead(entity);

        // Devuelve respuesta local
        return LeadResponseDto.builder()
                .campana(entity.getCampana())
                .resultado("OK")
                .telefono(entity.getTelefono())
                .build();
    }

    private void guardarYProcesar(LeadRequestDto dto) {
        LeadTemporalEntity entity = LeadMapper.toEntity(dto);
        leadTemporalRepository.save(entity);
        procesarAltaLead(entity);
    }

    // 2. Procesar alta de lead (simula sp_AltaLead)
    @Override
    @Transactional
    public void procesarAltaLead(LeadTemporalEntity lead) {
        try {
            // Validación de negocio
            if (!validarNegocio(lead)) {
                registrarLogCarga(
                        lead.getId(),
                        safeParseInt(lead.getCampana()),
                        lead.getCodProveedor(),
                        "Validación de negocio fallida",
                        "AltaLead"
                );
                registrarLogError("AltaLead", "Validación negocio fallida", toJson(lead));
                throw new BusinessException("Validación negocio fallida");
            }

            boolean cargaOk = cargarCampaña(lead);
            if (!cargaOk) {
                registrarLogCarga(
                        lead.getId(),
                        safeParseInt(lead.getCampana()),
                        lead.getCodProveedor(),
                        "Error en carga de campaña",
                        "CargaCampaña"
                );
                registrarLogError("CargaCampaña", "Error carga campaña", toJson(lead));
                throw new BusinessException("Error en carga de campaña");
            }

            // Si fue exitoso, se registra el éxito
            registrarLogCarga(
                    lead.getId(),
                    safeParseInt(lead.getCampana()),
                    lead.getCodProveedor(),
                    "Carga exitosa",
                    "AltaLead"
            );

            disociarLead(lead);
        } catch (Exception e) {
            //cualquier excepción no controlada
            registrarLogCarga(
                    lead.getId(),
                    safeParseInt(lead.getCampana()),
                    lead.getCodProveedor(),
                    "Excepción: " + e.getMessage(),
                    "AltaLead"
            );
            throw e;
        }
    }


    // 3. Disociar (simulamos sp_Disociar)
    @Override
    @Transactional
    public void disociarLead(LeadTemporalEntity lead) {
        // Buscar campos a cifrar: AUX_DISOCIAR (por defecto) y AUX_CAMPANA_DISOCIAR (por campaña)
        List<String> camposDefecto = auxDisociarRepository.findAllCampos();
        Integer campanaId = null;
        if (lead.getCampana() != null) {
            try {
                campanaId = Integer.valueOf(lead.getCampana());
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("El campo 'campana' no es un número válido: " + lead.getCampana());
            }
        }

        List<String> camposExtra = campanaId != null
                ? auxCampanaDisociarRepository.findCamposPorCampana(campanaId)
                : Collections.emptyList();

        LeadDisociadoEntity disociado = LeadDisociadoMapper.fromTemporal(
                lead, camposDefecto, camposExtra);

        leadDisociadoRepository.save(disociado);
        leadTemporalRepository.delete(lead);
    }

    //Reintento de cargas (simulamos sp_Recargar)
//    @Override
//    @Scheduled(cron = "0 */10 * * * *") // Cada 10 min
//    @Transactional
//    public void reintentarCargasFallidas() {
//        List<LeadTemporalEntity> pendientes = leadTemporalRepository.findPendientes();
//        for (LeadTemporalEntity lead : pendientes) {
//            try {
//                procesarAltaLead(lead);
//            } catch (Exception e) {
//                registrarLogError("Retry", e.getMessage(), toJson(lead));
//            }
//        }
//    }

    // 4. Consulta de leads disociados (simulamos sp_vDisociados)
    @Override
    @Transactional(readOnly = true)
    public LeadDisociadoDto verLeadDisociado(Long id) {

        if (id == null) throw new IllegalArgumentException("ID no puede ser null");
        if (id > Integer.MAX_VALUE || id < Integer.MIN_VALUE) {
            throw new IllegalArgumentException("ID fuera de rango Integer");
        }
        Integer idInt = id.intValue();

        LeadDisociadoEntity lead = leadDisociadoRepository.findById(idInt)
                .orElseThrow(() -> new ResourceNotFoundException("No existe lead disociado"));
        return LeadDisociadoMapper.toDto(lead, obtenerClaveDesencriptacion());
    }

    // 5. Registro de logs/auditoría
    @Override
    public void registrarLogError(String origen, String error, String cuerpoJson) {
        LogWebServiceEntity log = LogWebServiceEntity.builder()
                .cuerpo(cuerpoJson)
                .error(error)
                .fecha(LocalDateTime.now())
                .build();
        logWsRepository.save(log);
    }

    public void registrarLogCarga(String idLead, Integer campana, String proveedor, String logTexto, String comando) {
        LogCargaEntity log = LogCargaEntity.builder()
                .idLead(idLead)
                .campana(campana)
                .proveedor(proveedor)
                .logTexto(logTexto)
                .comando(comando)
                .fecha(LocalDateTime.now())
                .build();
        logsCargaRepository.save(log);
    }

    // validación usando Bean Validation
    private void validarDto(LeadRequestDto dto) {
        Set<ConstraintViolation<LeadRequestDto>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private boolean validarNegocio(LeadTemporalEntity lead) {
        // podriamos verificar si la campaña está activa, si acepta duplicados,etc.
        return true;
    }

    private boolean cargarCampaña(LeadTemporalEntity lead) {
        //simula carga
        return true;
    }

    private boolean esRespuestaOk(LeadResponseDto resp) {
        return resp != null && resp.getResultado() != null && resp.getResultado().toUpperCase().startsWith("OK");
    }

    private String toJson(Object obj) {
        try {
            return JsonUtil.toJson(obj);
        } catch (Exception e) {
            return "{}";
        }
    }

    private String obtenerClaveDesencriptacion() {
        return "claveDummy"; // Simulado
    }

    //convertir String a Integer (si es null o inválido devuelve null)
    private Integer safeParseInt(String value) {
        try {
            return (value != null) ? Integer.valueOf(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

