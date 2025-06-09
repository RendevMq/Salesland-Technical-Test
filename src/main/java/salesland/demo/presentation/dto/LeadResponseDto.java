package salesland.demo.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeadResponseDto {
    @JsonProperty("CAMPANA")
    private String campana;
    @JsonProperty("RESULTADO")
    private String resultado;
    @JsonProperty("TELEFONO")
    private String telefono;
}
