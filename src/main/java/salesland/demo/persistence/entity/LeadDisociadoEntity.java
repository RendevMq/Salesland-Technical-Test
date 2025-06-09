package salesland.demo.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ws_leads_disociados")
public class LeadDisociadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDENT")
    private Integer ident;

    @Column(name = "IDENT_ORI")
    private Integer identOri;

    @Column(name = "id_time_stamp", length = 50)
    private String idTimeStamp;

    @Column(name = "id_unico", length = 50)
    private String idUnico;

    @Column(name = "FECHA_ENTRADA")
    private LocalDateTime fechaEntrada;

    @Column(name = "duplicado")
    private Short duplicado;

    @Column(name = "cod_proveedor", length = 5)
    private String codProveedor;

    @Column(name = "id", length = 50)
    private String id;

    @Column(name = "campana", length = 50)
    private String campana;

    @Column(name = "fecha_captacion")
    private LocalDateTime fechaCaptacion;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "ape1", length = 50)
    private String ape1;

    @Column(name = "ape2", length = 50)
    private String ape2;

    @Column(name = "telefono", length = 9)
    private String telefono;

    @Column(name = "telefonoMD5", length = 50)
    private String telefonoMD5;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "acepta1", length = 2)
    private String acepta1;

    @Column(name = "acepta2", length = 2)
    private String acepta2;

    @Column(name = "acepta3", length = 2)
    private String acepta3;

    @Column(name = "num1")
    private Integer num1;

    @Column(name = "num2")
    private Integer num2;

    @Column(name = "num3")
    private Integer num3;

    @Column(name = "dual1", length = 2)
    private String dual1;

    @Column(name = "dual2", length = 2)
    private String dual2;

    @Column(name = "dual3", length = 2)
    private String dual3;

    @Column(name = "variable1", length = 50)
    private String variable1;

    @Column(name = "variable2", length = 50)
    private String variable2;

    @Column(name = "variable3", length = 50)
    private String variable3;


    @Column(name = "memo")
    private String memo;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "foto1", length = 500)
    private String foto1;

    @Column(name = "foto2", length = 500)
    private String foto2;

    @Column(name = "comercial", length = 50)
    private String comercial;

    @Column(name = "centro", length = 50)
    private String centro;

    @Column(name = "codigo_postal", length = 5)
    private String codigoPostal;

    @Column(name = "direccion", length = 50)
    private String direccion;

    @Column(name = "poblacion", length = 50)
    private String poblacion;

    @Column(name = "provincia", length = 50)
    private String provincia;

    @Column(name = "nif", length = 50)
    private String nif;

    @Column(name = "cargado")
    private Short cargado;

    @Column(name = "fecha_carga")
    private LocalDateTime fechaCarga;

    @Column(name = "fecha_disociado")
    private LocalDateTime fechaDisociado;

    // --- Campos encriptados ---

    @Column(name = "nombre_enc")
    private byte[] nombreEnc;

    @Column(name = "ape1_enc")
    private byte[] ape1Enc;

    @Column(name = "ape2_enc")
    private byte[] ape2Enc;

    @Column(name = "telefono_enc")
    private byte[] telefonoEnc;

    @Column(name = "email_enc")
    private byte[] emailEnc;

    @Column(name = "variable1_enc")
    private byte[] variable1Enc;

    @Column(name = "variable2_enc")
    private byte[] variable2Enc;

    @Column(name = "variable3_enc")
    private byte[] variable3Enc;

    @Column(name = "memo_enc")
    private byte[] memoEnc;

    @Column(name = "foto1_enc")
    private byte[] foto1Enc;

    @Column(name = "foto2_enc")
    private byte[] foto2Enc;

    @Column(name = "codigo_postal_enc")
    private byte[] codigoPostalEnc;

    @Column(name = "direccion_enc")
    private byte[] direccionEnc;

    @Column(name = "poblacion_enc")
    private byte[] poblacionEnc;

    @Column(name = "provincia_enc")
    private byte[] provinciaEnc;

    @Column(name = "nif_enc")
    private byte[] nifEnc;
}
