package salesland.demo.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeadDisociadoDto {
    private Long id;
    private String idTimeStamp;
    private String idUnico;
    private String fechaEntrada;
    private Boolean duplicado;
    private String codProveedor;
    private String idLead;
    private String campana;
    private String fechaCaptacion;
    private String nombre;
    private String ape1;
    private String ape2;
    private String telefono;
    private String telefonoMD5;
    private String email;
    private String acepta1;
    private String acepta2;
    private String acepta3;
    private Integer num1;
    private Integer num2;
    private Integer num3;
    private String dual1;
    private String dual2;
    private String dual3;
    private String variable1;
    private String variable2;
    private String variable3;
    private String memo;
    private String fecha;
    private String hora;
    private String foto1;
    private String foto2;
    private String comercial;
    private String centro;
    private String codigoPostal;
    private String direccion;
    private String poblacion;
    private String provincia;
    private String nif;
    private Boolean cargado;
    private String fechaCarga;
    private String fechaDisociado;
}
