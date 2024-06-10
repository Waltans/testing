package com.urfu.testing.application.serviceImpl;

import com.urfu.testing.application.services.ModuleService;
import com.urfu.testing.domain.Module;
import com.urfu.testing.domain.Type;
import com.urfu.testing.extern.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleServiceImplement implements ModuleService {

    private final ModuleRepository moduleRepository;

    @Override
    public Optional<Module> getModule(UUID uuid) {
        return this.moduleRepository.findById(uuid);
    }

    @Override
    public List<Module> getAllModules() {
        return this.moduleRepository.findAll();
    }

    @Override
    public void updateModule(UUID uuid, String title, Type type) {
        Module module = this.moduleRepository.findById(uuid).orElse(null);
        if (module != null) {
            if (title != null) module.setTitle(title);
            if (type != null) module.setType(type);
        } else throw new IllegalArgumentException("Module not found");
    }

    @Override
    public void deleteModule(UUID uuid) {
        this.moduleRepository.deleteById(uuid);
    }

    @Override
    public Module saveModule(Module module) {
        return this.moduleRepository.save(module);
    }
}
