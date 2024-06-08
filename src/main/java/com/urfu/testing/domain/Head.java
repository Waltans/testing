package com.urfu.testing.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Ответственное лицо имеет следующие поля:
 */
@Entity
@Data
@NoArgsConstructor
public class Head {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String fullName;

    @OneToMany(mappedBy = "head", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<EducationProgram> educationPrograms;
}
