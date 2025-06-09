package salesland.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import salesland.demo.persistence.entity.LeadTemporalEntity;

import java.util.List;

@Repository
public interface LeadTemporalRepository extends JpaRepository<LeadTemporalEntity, Integer> {
    // Busca leads donde cargado es null o 0 (pendiente)
    @Query("SELECT l FROM LeadTemporalEntity l WHERE l.cargado IS NULL OR l.cargado = 0")
    List<LeadTemporalEntity> findPendientes();
}

