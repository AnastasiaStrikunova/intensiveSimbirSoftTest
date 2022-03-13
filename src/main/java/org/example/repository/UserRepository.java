package org.example.repository;

import org.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Integer countByEmail(String email);
    @Query(value = "SELECT users.id_user FROM users WHERE users.email = ?1 AND users.password = ?2", nativeQuery = true)
    Long getIdByEmailAndPassword(String email, String password);
}
