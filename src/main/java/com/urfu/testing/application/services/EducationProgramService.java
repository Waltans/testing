package com.urfu.testing.application.services;

import com.urfu.testing.domain.Module;
import com.urfu.testing.domain.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EducationProgramService {

    /**
     * Метод для поиска программы по Id
     *
     * @param id - uuid
     * @return - optional<EducationProgram>
     */
    Optional<EducationProgram> findById(UUID id);

    /**
     * Сохраняет образовательную программу
     *
     * @param educationProgram - ОП
     * @return сохраненная ОП
     */
    EducationProgram save(EducationProgram educationProgram);

    /**
     * Удалить ОП
     *
     * @param uuid - uuid для удаления
     */
    void delete(UUID uuid);

    /**
     * Обновляет ОП
     *
     * @param uuid              - uuid
     * @param title             - название
     * @param cypher            - шифр
     * @param level             - уровень обучения
     * @param standard          - стандарт
     * @param institute         - институт
     * @param head              - ответственное лицо
     * @param accreditationTime - дата следующей аккредитации
     * @param module            - модуль
     */
    void update(UUID uuid, String title, String cypher, Level level, Standard standard,
                Institute institute, Head head, Date accreditationTime, Module module);

    /**
     * Метод для получения всех образовательных программ
     *
     * @param pageable - пагинация
     * @return - страницу с образовательными программами
     */
    List<EducationProgram> findAll();
}