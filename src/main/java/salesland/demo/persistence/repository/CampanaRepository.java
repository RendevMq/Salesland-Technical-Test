package salesland.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salesland.demo.persistence.entity.CampanaEntity;

import java.util.Optional;

@Repository
public interface CampanaRepository extends JpaRepository<CampanaEntity, Integer> {
    Optional<CampanaEntity> findByIdent(Integer ident);

    Optional<CampanaEntity> findByIdCampana(Integer idCampana);
}

