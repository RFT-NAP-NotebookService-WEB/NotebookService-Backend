package hu.unideb.inf.notebookservice.persistence.repository;

import hu.unideb.inf.notebookservice.persistence.entity.ModificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModificationRepository extends JpaRepository<ModificationEntity, Long> {

    Optional<ModificationEntity> findByName(String name);
}
