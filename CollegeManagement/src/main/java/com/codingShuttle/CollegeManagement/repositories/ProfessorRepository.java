package com.codingShuttle.CollegeManagement.repositories;

import com.codingShuttle.CollegeManagement.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}