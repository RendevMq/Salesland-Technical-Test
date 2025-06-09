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

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aux_campanas")
public class CampanaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDENT")
    private Integer ident;

    @Column(name = "servidor", length = 50, nullable = false)
    private String servidor;

    @Column(name = "bbdd_report", length = 50)
    private String bbddReport;

    @Column(name = "id_campana", nullable = false)
    private Integer idCampana;

    @Column(name = "sistema", length = 50, nullable = false)
    private String sistema;

    @Column(name = "Nombre", length = 50)
    private String nombre;

    @Column(name = "activo")
    private Short activo;

    @Column(name = "spcarga_ws_salesland_leads", length = 50)
    private String spcargaWsSaleslandLeads;

    @Column(name = "admite_duplicado")
    private Short admiteDuplicado;
}

