package com.urfu.testing.extern.repository;

import com.urfu.testing.domain.Institute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstituteRepository extends CrudRepository<Institute, UUID> {
}
