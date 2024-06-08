package com.urfu.testing.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

/**
 * Класс с описанием образовательной программы
 */
@Entity
@Getter
@Setter
public class EducationProgram {
    /**
     * идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Название
     */
    private String title;

    /**
     * Шифр
     */
    private String cypher;

    /**
     * Уровень обучения
     */
    private Level level;

    /**
     * Стандарт обучения
     */
    private Standard standard;

    /**
     * Институт (нужна справочная таблица, из которой
     * можно будет выбирать институт для программы)
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "institute_id")
    private Institute institute;

    /**
     * Ответственное лицо (нужна справочная таблица, из
     * которой можно будет выбирать ответственное лицо
     * программы)
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "head_id")
    private Head head;

    /**
     * Дата следующей аккредитации
     */
    private Date accreditationTime;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "education_program_id")
    private Module module;
}
