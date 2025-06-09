package salesland.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import salesland.demo.persistence.entity.CampoDisociarCampanaEntity;

import java.util.List;

@Repository
public interface CampoDisociarCampanaRepository extends JpaRepository<CampoDisociarCampanaEntity, Long> {
    @Query("SELECT c.campo FROM CampoDisociarCampanaEntity c WHERE c.campana = :campana")
    List<String> findCamposPorCampana(@Param("campana") Integer campana);
}
