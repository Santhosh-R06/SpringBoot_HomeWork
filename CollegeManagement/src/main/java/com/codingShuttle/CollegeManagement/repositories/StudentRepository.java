package com.codingShuttle.CollegeManagement.repositories;

import com.codingShuttle.CollegeManagement.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}