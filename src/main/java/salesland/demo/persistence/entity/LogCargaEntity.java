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

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "logs_carga")
public class LogCargaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDENT")
    private Integer ident;

    @Column(name = "IdLead", length = 50)
    private String idLead;

    @Column(name = "campana")
    private Integer campana;

    @Column(name = "proveedor", length = 50)
    private String proveedor;

    @Column(name = "log_texto", length = 4000)
    private String logTexto;

    @Column(name = "comando", length = 4000)
    private String comando;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}
