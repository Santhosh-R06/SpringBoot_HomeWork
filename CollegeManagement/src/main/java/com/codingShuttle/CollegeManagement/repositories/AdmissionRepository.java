package com.codingShuttle.CollegeManagement.repositories;

import com.codingShuttle.CollegeManagement.entities.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
}