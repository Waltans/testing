package com.urfu.testing.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Институт
 */
@Entity
@Data
@NoArgsConstructor
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    /**
     * Название
     */
    private String title;

    /**
     * Список образовательных программ
     */
    @OneToMany(mappedBy = "institute",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<EducationProgram> educationPrograms;
}
