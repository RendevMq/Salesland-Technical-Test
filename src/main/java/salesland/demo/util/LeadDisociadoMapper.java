package salesland.demo.util;

import salesland.demo.persistence.entity.LeadDisociadoEntity;
import salesland.demo.persistence.entity.LeadTemporalEntity;
import salesland.demo.presentation.dto.LeadDisociadoDto;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


public class LeadDisociadoMapper {

    // Convierte LeadTemporalEntity a LeadDisociadoEntity, cifrando campos sensibles
    public static LeadDisociadoEntity fromTemporal(
            LeadTemporalEntity temporal,
            List<String> camposDefecto,
            List<String> camposExtra) {

        Set<String> camposACifrar = new HashSet<>();
        if (camposDefecto != null) camposACifrar.addAll(camposDefecto);
        if (camposExtra != null) camposACifrar.addAll(camposExtra);

        Function<String, byte[]> cifrador = val -> val != null ?
                Base64.getEncoder().encode(val.getBytes(StandardCharsets.UTF_8)) : null;

        return LeadDisociadoEntity.builder()
//                .id(temporal.getId())
                .campana(temporal.getCampana())
                .codProveedor(temporal.getCodProveedor())
                .fechaCaptacion(temporal.getFechaCaptacion())
                .idUnico(temporal.getId())
                // --- Campos susceptibles de cifrado
                .nombre(camposACifrar.contains("nombre") ? null : temporal.getNombre())
                .nombreEnc(camposACifrar.contains("nombre") ? cifrador.apply(temporal.getNombre()) : null)
                .ape1(camposACifrar.contains("ape1") ? null : temporal.getApe1())
                .ape1Enc(camposACifrar.contains("ape1") ? cifrador.apply(temporal.getApe1()) : null)
                .ape2(temporal.getApe2())
                .nif(camposACifrar.contains("nif") ? null : temporal.getNif())
                .nifEnc(camposACifrar.contains("nif") ? cifrador.apply(temporal.getNif()) : null)
                .telefono(camposACifrar.contains("telefono") ? null : temporal.getTelefono())
                .telefonoEnc(camposACifrar.contains("telefono") ? cifrador.apply(temporal.getTelefono()) : null)
                .email(camposACifrar.contains("email") ? null : temporal.getEmail())
                .emailEnc(camposACifrar.contains("email") ? cifrador.apply(temporal.getEmail()) : null)
                .direccion(camposACifrar.contains("direccion") ? null : temporal.getDireccion())
                .direccionEnc(camposACifrar.contains("direccion") ? cifrador.apply(temporal.getDireccion()) : null)
                // Otros campos normales (no cifrados)
                .codigoPostal(temporal.getCodigoPostal())
                .poblacion(temporal.getPoblacion())
                .provincia(temporal.getProvincia())
                .acepta1(temporal.getAcepta1())
                .acepta2(temporal.getAcepta2())
                .acepta3(temporal.getAcepta3())
                .num1(temporal.getNum1())
                .num2(temporal.getNum2())
                .num3(temporal.getNum3())
                .dual1(temporal.getDual1())
                .dual2(temporal.getDual2())
                .dual3(temporal.getDual3())
                .variable1(temporal.getVariable1())
                .variable2(temporal.getVariable2())
                .variable3(temporal.getVariable3())
                .memo(temporal.getMemo())
                .fecha(temporal.getFecha())
                .hora(temporal.getHora())
                .foto1(temporal.getFoto1())
                .foto2(temporal.getFoto2())
                .comercial(temporal.getComercial())
                .centro(temporal.getCentro())
                .build();
    }

    // Convierte LeadDisociadoEntity a LeadDisociadoDto, DESCIFRANDO si corresponde
    public static LeadDisociadoDto toDto(LeadDisociadoEntity entity, String clave) {
        // Simula desencriptado, solo para ejemplo. En la vida real usarías la clave.
        Function<byte[], String> decifrador = val -> val != null ?
                new String(Base64.getDecoder().decode(val), StandardCharsets.UTF_8) : null;

        return LeadDisociadoDto.builder()
                .id(entity.getIdent() != null ? entity.getIdent().longValue() : null)
                .campana(entity.getCampana())
                .codProveedor(entity.getCodProveedor())
                .fechaCaptacion(entity.getFechaCaptacion() != null ? entity.getFechaCaptacion().toString() : null)
                .nombre(entity.getNombre() != null ? entity.getNombre() : decifrador.apply(entity.getNombreEnc()))
                .ape1(entity.getApe1() != null ? entity.getApe1() : decifrador.apply(entity.getApe1Enc()))
                .ape2(entity.getApe2())
                .nif(entity.getNif() != null ? entity.getNif() : decifrador.apply(entity.getNifEnc()))
                .telefono(entity.getTelefono() != null ? entity.getTelefono() : decifrador.apply(entity.getTelefonoEnc()))
                .email(entity.getEmail() != null ? entity.getEmail() : decifrador.apply(entity.getEmailEnc()))
                .direccion(entity.getDireccion() != null ? entity.getDireccion() : decifrador.apply(entity.getDireccionEnc()))
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
                .fecha(entity.getFecha() != null ? entity.getFecha().toString() : null)
                .hora(entity.getHora() != null ? entity.getHora().toString() : null)
                .foto1(entity.getFoto1())
                .foto2(entity.getFoto2())
                .comercial(entity.getComercial())
                .centro(entity.getCentro())
                .build();
    }
}

