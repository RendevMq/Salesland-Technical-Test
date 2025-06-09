package salesland.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import salesland.demo.persistence.entity.CampoDisociarEntity;

import java.util.List;

@Repository
public interface CampoDisociarRepository extends JpaRepository<CampoDisociarEntity, String> {
    @Query("SELECT c.campo FROM CampoDisociarEntity c")
    List<String> findAllCampos();
}

