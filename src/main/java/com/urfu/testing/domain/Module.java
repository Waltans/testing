package com.urfu.testing.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "module", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<EducationProgram> educationPrograms;
}
