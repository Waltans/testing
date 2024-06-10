package com.urfu.testing.application.services;

import com.urfu.testing.domain.Module;
import com.urfu.testing.domain.Type;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleService {

    Optional<Module> getModule(UUID uuid);

    List<Module> getAllModules();

    void updateModule(UUID uuid,String title, Type type);

    void deleteModule(UUID uuid);

    Module saveModule(Module module);
}
