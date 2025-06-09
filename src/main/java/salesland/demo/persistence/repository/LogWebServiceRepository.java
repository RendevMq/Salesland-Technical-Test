package salesland.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salesland.demo.persistence.entity.LogWebServiceEntity;

@Repository
public interface LogWebServiceRepository extends JpaRepository<LogWebServiceEntity, Integer> {
}
