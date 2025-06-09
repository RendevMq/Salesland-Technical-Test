package salesland.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salesland.demo.persistence.entity.LogCargaEntity;

@Repository
public interface LogCargaRepository extends JpaRepository<LogCargaEntity, Integer> {
}
