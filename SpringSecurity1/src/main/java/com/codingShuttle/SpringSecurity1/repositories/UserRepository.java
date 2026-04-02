package com.codingShuttle.SpringSecurity1.repositories;


import com.codingShuttle.SpringSecurity1.dto.SignUpDto;
import com.codingShuttle.SpringSecurity1.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
