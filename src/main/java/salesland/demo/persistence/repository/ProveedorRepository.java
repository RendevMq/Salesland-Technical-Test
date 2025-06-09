package salesland.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salesland.demo.persistence.entity.ProveedorEntity;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {
    Optional<ProveedorEntity> findByCodProveedor(String codProveedor);
}
