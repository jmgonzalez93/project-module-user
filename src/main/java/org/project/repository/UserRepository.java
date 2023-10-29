package org.project.repository;

import org.project.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDataEntity, UUID> {

    UserDataEntity findByEmailAndToken(String email, UUID token);

    UserDataEntity findByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_data SET last_login = :lastLogin WHERE id = :id", nativeQuery = true)
    void updateBylastLogin(@Param("lastLogin") LocalDateTime lastLogin, @Param("id") UUID id);

}
