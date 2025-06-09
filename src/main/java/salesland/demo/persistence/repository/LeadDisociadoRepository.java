package salesland.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salesland.demo.persistence.entity.LeadDisociadoEntity;

import java.util.Optional;

@Repository
public interface LeadDisociadoRepository extends JpaRepository<LeadDisociadoEntity, Integer> {
    Optional<LeadDisociadoEntity> findByIdent(Integer ident);
}
