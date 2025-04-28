package com.ies.UserMS.repository;

import com.ies.UserMS.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    public Optional<UserEntity> findByEmail(String email);
}
