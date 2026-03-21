package com.codingShuttle.CollegeManagement.repositories;

import com.codingShuttle.CollegeManagement.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}