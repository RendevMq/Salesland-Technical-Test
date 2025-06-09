package salesland.demo.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeadRequestDto {

    @NotBlank(message = "El ID es obligatorio")
    @Size(max = 50, message = "El ID no debe exceder los 50 caracteres")
    private String id;

    @NotNull(message = "La campaña es obligatoria")
    private Integer campana;

    @NotBlank(message = "El código de proveedor es obligatorio")
    @Size(max = 5, message = "El código de proveedor no debe exceder los 5 caracteres")
    private String codProveedor;

    @NotBlank(message = "La fecha de captación es obligatoria (formato: yyyyMMdd HH:mm)")
    @Size(max = 16, message = "La fecha de captación no debe exceder los 16 caracteres")
    private String fechaCaptacion;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no debe exceder los 50 caracteres")
    private String nombre;

    @Size(max = 50, message = "El primer apellido no debe exceder los 50 caracteres")
    private String ape1;

    @Size(max = 50, message = "El segundo apellido no debe exceder los 50 caracteres")
    private String ape2;

    @Size(max = 50, message = "El NIF no debe exceder los 50 caracteres")
    private String nif;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[6789]\\d{8}$", message = "El teléfono debe tener 9 cifras y comenzar con 6, 7, 8 o 9")
    private String telefono;

    @Email(message = "El email debe ser válido")
    @Size(max = 150, message = "El email no debe exceder los 150 caracteres")
    private String email;

    @Size(max = 50, message = "La dirección no debe exceder los 50 caracteres")
    private String direccion;

    @Size(max = 5, message = "El código postal no debe exceder los 5 caracteres")
    private String codigoPostal;

    @Size(max = 50, message = "La población no debe exceder los 50 caracteres")
    private String poblacion;

    @Size(max = 50, message = "La provincia no debe exceder los 50 caracteres")
    private String provincia;

    @NotBlank(message = "El campo 'acepta1' es obligatorio")
    @Pattern(regexp = "^(SI|NO)$", message = "El campo 'acepta1' debe ser SI o NO")
    private String acepta1;

    @Pattern(regexp = "^(SI|NO)?$", message = "El campo 'acepta2' debe ser SI, NO o vacío")
    private String acepta2;

    @Pattern(regexp = "^(SI|NO)?$", message = "El campo 'acepta3' debe ser SI, NO o vacío")
    private String acepta3;

    private Integer num1;
    private Integer num2;
    private Integer num3;

    @Pattern(regexp = "^(SI|NO)?$", message = "El campo 'dual1' debe ser SI, NO o vacío")
    private String dual1;

    @Pattern(regexp = "^(SI|NO)?$", message = "El campo 'dual2' debe ser SI, NO o vacío")
    private String dual2;

    @Pattern(regexp = "^(SI|NO)?$", message = "El campo 'dual3' debe ser SI, NO o vacío")
    private String dual3;

    @Size(max = 50, message = "El campo 'variable1' no debe exceder los 50 caracteres")
    private String variable1;

    @Size(max = 50, message = "El campo 'variable2' no debe exceder los 50 caracteres")
    private String variable2;

    @Size(max = 50, message = "El campo 'variable3' no debe exceder los 50 caracteres")
    private String variable3;

    @Size(max = 8000, message = "El campo 'memo' no debe exceder los 8000 caracteres")
    private String memo;

    @Size(max = 8, message = "La fecha no debe exceder los 8 caracteres (formato: yyyyMMdd)")
    private String fecha;

    @Size(max = 5, message = "La hora no debe exceder los 5 caracteres (formato: HH:mm)")
    private String hora;

    @Size(max = 500, message = "La ruta de la foto 1 no debe exceder los 500 caracteres")
    private String foto1;

    @Size(max = 500, message = "La ruta de la foto 2 no debe exceder los 500 caracteres")
    private String foto2;

    @NotBlank(message = "El campo 'comercial' es obligatorio")
    @Size(max = 50, message = "El campo 'comercial' no debe exceder los 50 caracteres")
    private String comercial;

    @Size(max = 50, message = "El campo 'centro' no debe exceder los 50 caracteres")
    private String centro;
}
