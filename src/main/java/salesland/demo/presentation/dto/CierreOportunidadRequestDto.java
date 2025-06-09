package salesland.demo.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CierreOportunidadRequestDto {

    @NotBlank(message = "El id de oportunidad es obligatorio")
    private String id_oportunidad;

    @NotBlank(message = "La descripción (código de cierre) es obligatoria")
    private String descripcion;
}
