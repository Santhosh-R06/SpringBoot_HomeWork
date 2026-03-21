package com.codingShuttle.CollegeManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @ManyToMany(mappedBy = "students")
    private List<Professor> professors;

    @ManyToMany
    @JoinColumn(name = "student_subject")
    private List<Subject> subjects;
}
