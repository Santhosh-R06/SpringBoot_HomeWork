package com.codingShuttle.SpringSecurity1.repositories;

import com.codingShuttle.SpringSecurity1.entities.CubeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CubeRepository extends JpaRepository<CubeEntity, Long> {
}
