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


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aux_campana_disociar")
public class CampoDisociarCampanaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Se agrega este campo para PK en JPA

    @Column(name = "campana", nullable = false)
    private Integer campana; // Referencia a AUX_CAMPANAS.IDENT

    @Column(name = "campo", length = 50, nullable = false)
    private String campo;

    @Column(name = "tipo", length = 50)
    private String tipo;
}
