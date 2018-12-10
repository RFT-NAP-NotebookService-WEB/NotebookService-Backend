package hu.unideb.inf.notebookservice.persistence.repository;

import hu.unideb.inf.notebookservice.persistence.entity.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Query("select count(u) > 0 from UserEntity u")
    boolean anyExists();
}
