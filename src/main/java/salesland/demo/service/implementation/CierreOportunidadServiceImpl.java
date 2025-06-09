package salesland.demo.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import salesland.demo.persistence.entity.LeadDisociadoEntity;
import salesland.demo.persistence.entity.LogWebServiceEntity;
import salesland.demo.persistence.repository.LeadDisociadoRepository;
import salesland.demo.persistence.repository.LogWebServiceRepository;
import salesland.demo.presentation.dto.CierreOportunidadRequestDto;
import salesland.demo.presentation.dto.CierreOportunidadResponseDto;
import salesland.demo.service.exception.ResourceNotFoundException;
import salesland.demo.service.interfaces.CierreOportunidadService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CierreOportunidadServiceImpl implements CierreOportunidadService {

    @Autowired
    private final LeadDisociadoRepository leadDisociadoRepository;
    @Autowired
    private final LogWebServiceRepository logWsRepository;

    @Override
    public CierreOportunidadResponseDto cerrarOportunidad(CierreOportunidadRequestDto request) {
        // Extraes los campos desde el DTO
        String idOportunidad = request.getId_oportunidad();
        String descripcion = request.getDescripcion();

        Optional<LeadDisociadoEntity> leadOpt = leadDisociadoRepository.findByIdent(Integer.parseInt(idOportunidad));
        if (leadOpt.isEmpty()) {
            throw new ResourceNotFoundException("No existe oportunidad con ese ID");
        }

        String payload = "{\"id_oportunidad\":\"" + idOportunidad + "\",\"descripcion\":\"" + descripcion + "\"}";
        String respuestaSimulada = "{\"status\":\"OK\",\"message\":\"Cierre registrado\"}";

        registrarLogErrorCierre("CIERRE_OPORTUNIDAD", respuestaSimulada, payload);

        return CierreOportunidadResponseDto.builder()
                .status("OK")
                .resultado("Oportunidad cerrada exitosamente (simulado)")
                .build();
    }

    @Override
    public void registrarLogErrorCierre(String origen, String error, String cuerpoJson) {
        LogWebServiceEntity log = LogWebServiceEntity.builder()
                .cuerpo(cuerpoJson)
                .error(error)
                .fecha(LocalDateTime.now())
                .build();
        logWsRepository.save(log);
    }
}

