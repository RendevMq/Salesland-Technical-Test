package salesland.demo.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import salesland.demo.persistence.entity.LeadDisociadoEntity;
import salesland.demo.persistence.entity.LeadTemporalEntity;
import salesland.demo.presentation.dto.LeadRequestDto;
import salesland.demo.presentation.dto.LeadResponseDto;
import salesland.demo.service.exception.LeadFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LeadMapper {

    // LeadRequestDto → LeadTemporalEntity
    public static LeadTemporalEntity toEntity(LeadRequestDto dto) {
        if (dto == null) return null;

        // Parseo para fecha_captacion: "yyyyMMdd HH:mm"
        LocalDateTime fechaCaptacion = null;
        if (dto.getFechaCaptacion() != null && !dto.getFechaCaptacion().isBlank()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");
                fechaCaptacion = LocalDateTime.parse(dto.getFechaCaptacion(), formatter);
            } catch (Exception e) {
                throw new LeadFormatException("Formato de fecha_captacion inválido, se esperaba yyyyMMdd HH:mm");
            }
        }

        // Parseo para fecha: "yyyyMMdd"
        LocalDate fecha = null;
        if (dto.getFecha() != null && !dto.getFecha().isBlank()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                fecha = LocalDate.parse(dto.getFecha(), formatter);
            } catch (Exception e) {
                throw new LeadFormatException("Formato de fecha inválido, se esperaba yyyyMMdd");
            }
        }

        // Parseo para hora: "HH:mm"
        LocalTime hora = null;
        if (dto.getHora() != null && !dto.getHora().isBlank()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                hora = LocalTime.parse(dto.getHora(), formatter);
            } catch (Exception e) {
                throw new LeadFormatException("Formato de hora inválido, se esperaba HH:mm");
            }
        }

        return LeadTemporalEntity.builder()
                .idUnico(dto.getId())
                .campana(dto.getCampana() != null ? dto.getCampana().toString() : null)
                .codProveedor(dto.getCodProveedor())
                .fechaCaptacion(fechaCaptacion)
                .nombre(dto.getNombre())
                .ape1(dto.getApe1())
                .ape2(dto.getApe2())
                .nif(dto.getNif())
                .telefono(dto.getTelefono())
                .email(dto.getEmail())
                .direccion(dto.getDireccion())
                .codigoPostal(dto.getCodigoPostal())
                .poblacion(dto.getPoblacion())
                .provincia(dto.getProvincia())
                .acepta1(dto.getAcepta1())
                .acepta2(dto.getAcepta2())
                .acepta3(dto.getAcepta3())
                .num1(dto.getNum1())
                .num2(dto.getNum2())
                .num3(dto.getNum3())
                .dual1(dto.getDual1())
                .dual2(dto.getDual2())
                .dual3(dto.getDual3())
                .variable1(dto.getVariable1())
                .variable2(dto.getVariable2())
                .variable3(dto.getVariable3())
                .memo(dto.getMemo())
                .fecha(fecha)
                .hora(hora)
                .foto1(dto.getFoto1())
                .foto2(dto.getFoto2())
                .comercial(dto.getComercial())
                .centro(dto.getCentro())
                .build();
    }

    // LeadTemporalEntity → LeadResponseDto
    public static LeadResponseDto toResponseDto(LeadTemporalEntity entity, String resultado) {
        if (entity == null) return null;

        return LeadResponseDto.builder()
                .campana(entity.getCampana())
                .resultado(resultado)
                .telefono(entity.getTelefono())
                .build();
    }

    public static LeadResponseDto parsearErrorApi(String json) {
        // Usa Jackson o similar para mapear el JSON de error al DTO
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, LeadResponseDto.class);
        } catch (Exception e) {
            return LeadResponseDto.builder()
                    .campana(null)
                    .resultado("KO: error parseando respuesta API")
                    .telefono(null)
                    .build();
        }
    }


    // LeadTemporalEntity → LeadDisociadoEntity
    // (se copia campos para el proceso de disociar)
    public static LeadDisociadoEntity toDisociadoEntity(LeadTemporalEntity entity) {
        if (entity == null) return null;

        return LeadDisociadoEntity.builder()
//                .id(entity.getId())
                .identOri(entity.getIdent())
                .idUnico(entity.getIdUnico())
                .campana(entity.getCampana())
                .codProveedor(entity.getCodProveedor())
                .fechaCaptacion(entity.getFechaCaptacion())
                .nombre(entity.getNombre())
                .ape1(entity.getApe1())
                .ape2(entity.getApe2())
                .nif(entity.getNif())
                .telefono(entity.getTelefono())
                .email(entity.getEmail())
                .direccion(entity.getDireccion())
                .codigoPostal(entity.getCodigoPostal())
                .poblacion(entity.getPoblacion())
                .provincia(entity.getProvincia())
                .acepta1(entity.getAcepta1())
                .acepta2(entity.getAcepta2())
                .acepta3(entity.getAcepta3())
                .num1(entity.getNum1())
                .num2(entity.getNum2())
                .num3(entity.getNum3())
                .dual1(entity.getDual1())
                .dual2(entity.getDual2())
                .dual3(entity.getDual3())
                .variable1(entity.getVariable1())
                .variable2(entity.getVariable2())
                .variable3(entity.getVariable3())
                .memo(entity.getMemo())
                .fecha(entity.getFecha())
                .hora(entity.getHora())
                .foto1(entity.getFoto1())
                .foto2(entity.getFoto2())
                .comercial(entity.getComercial())
                .centro(entity.getCentro())
                .build();
    }
}
