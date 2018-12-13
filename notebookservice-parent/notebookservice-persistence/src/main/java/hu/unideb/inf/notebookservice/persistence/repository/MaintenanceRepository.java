package hu.unideb.inf.notebookservice.persistence.repository;

import hu.unideb.inf.notebookservice.commons.enumeration.Status;
import hu.unideb.inf.notebookservice.persistence.entity.MaintenanceEntity;
import hu.unideb.inf.notebookservice.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {

    Optional<MaintenanceEntity> findByProduct(ProductEntity productEntity);

    List<MaintenanceEntity> findByStatus(Status status);
}
