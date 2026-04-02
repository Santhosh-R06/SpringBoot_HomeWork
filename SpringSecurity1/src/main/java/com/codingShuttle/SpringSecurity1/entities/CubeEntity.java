package com.codingShuttle.SpringSecurity1.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cubes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CubeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cubeArray;
}
