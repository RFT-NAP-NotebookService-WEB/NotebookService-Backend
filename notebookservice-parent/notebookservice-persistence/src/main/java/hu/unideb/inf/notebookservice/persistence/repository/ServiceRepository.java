package hu.unideb.inf.notebookservice.persistence.repository;

import hu.unideb.inf.notebookservice.persistence.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}