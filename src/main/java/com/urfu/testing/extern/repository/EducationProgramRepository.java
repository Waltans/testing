package com.urfu.testing.extern.repository;

import com.urfu.testing.domain.EducationProgram;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EducationProgramRepository extends CrudRepository<EducationProgram, UUID> {
    List<EducationProgram> findAll();
}
