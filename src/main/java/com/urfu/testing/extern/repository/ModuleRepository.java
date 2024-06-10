package com.urfu.testing.extern.repository;

import com.urfu.testing.domain.Module;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ModuleRepository extends CrudRepository<Module, UUID> {
    List<Module> findAll();
}
