package hu.unideb.inf.notebookservice.persistence.repository;

import hu.unideb.inf.notebookservice.persistence.entity.MaintenanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {
}
