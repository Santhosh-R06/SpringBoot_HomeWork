package com.codingShuttle.SpringSecurity1.repositories;

import com.codingShuttle.SpringSecurity1.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    List<SessionEntity> findByUserId(String userId);
}
