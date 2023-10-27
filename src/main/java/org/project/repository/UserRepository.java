package org.project.repository;

import org.project.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDataEntity, UUID> {

    UserDataEntity findByEmail(String email);

    boolean existsByEmail(String email);
}
