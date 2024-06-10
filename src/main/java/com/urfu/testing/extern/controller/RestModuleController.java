package com.urfu.testing.extern.controller;

import com.urfu.testing.application.services.ModuleService;
import com.urfu.testing.domain.Module;
import com.urfu.testing.extern.dto.ModuleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/modules")
public class RestModuleController {

    private final ModuleService moduleService;

    @GetMapping
    public ResponseEntity<List<Module>> getAllModules() {
        return ResponseEntity.ok(moduleService.getAllModules());
    }

    @PostMapping
    public ResponseEntity<?> addModule(ModuleDto moduleDto) {
        try {
            Module module = Module.builder()
                    .type(moduleDto.type())
                    .title(moduleDto.title())
                    .build();


            return ResponseEntity.ok(moduleService.saveModule(module));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<?> deleteModule(@PathVariable UUID uuid) {
        try {
            moduleService.deleteModule(uuid);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{uuid}")
    public ResponseEntity<?> updateModule(@PathVariable UUID uuid, ModuleDto moduleDto) {
        try {
            moduleService.updateModule(uuid, moduleDto.title(), moduleDto.type());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
