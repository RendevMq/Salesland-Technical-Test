package salesland.demo.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ErrorResponseDto {

    private String campana;   // Opcional, si aplica a la respuesta de error
    private String resultado; // Mensaje de error concreto (ej: KO: <detalle>)
    private String telefono;  // Opcional, si aplica a la respuesta de error

    // Campos adicionales propios SOLO cuando hay error
    private String message;                   // Mensaje general o del handler
    private String code;                      // Código (ejemplo, "400", "404")
    private LocalDateTime timestamp;          // Marca de tiempo del error (opcional)
    private Map<String, String> errors;       // Errores por campo (campo: mensaje)
}
