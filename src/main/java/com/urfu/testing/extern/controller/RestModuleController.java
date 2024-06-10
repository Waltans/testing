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

/**
 * API для взаимодействия с модулями
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/modules")
public class RestModuleController {

    private final ModuleService moduleService;

    /**
     * Получение всех модулей
     *
     * @return - список всех модулей
     */
    @GetMapping
    public ResponseEntity<List<Module>> getAllModules() {
        return ResponseEntity.ok(moduleService.getAllModules());
    }

    /**
     * Метод для добавления нового метода
     *
     * @param moduleDto - DTO модуля
     * @return - ResponseEntity с телом успешного сохранения или с ошибкой
     */
    @PostMapping
    public ResponseEntity<String> addModule(ModuleDto moduleDto) {
        try {
            Module module = Module.builder()
                    .type(moduleDto.type())
                    .title(moduleDto.title())
                    .build();
            moduleService.saveModule(module);
            return ResponseEntity.status(HttpStatus.CREATED).body("Module added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Удаление ОП
     *
     * @param uuid - uuid
     * @return - ResponseEntity c телом успешного удаления или с ошибкой
     */
    @DeleteMapping("{uuid}")
    public ResponseEntity<String> deleteModule(@PathVariable UUID uuid) {
        try {
            moduleService.deleteModule(uuid);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Метод для обновления модуля
     *
     * @param uuid      - uuid
     * @param moduleDto - DTO с обновленными данными
     * @return - ResponseEntity c телом успешного обновления или с ошибкой
     */
    @PutMapping("{uuid}")
    public ResponseEntity<String> updateModule(@PathVariable UUID uuid, ModuleDto moduleDto) {
        try {
            moduleService.updateModule(uuid, moduleDto.title(), moduleDto.type());
            return ResponseEntity.ok().body("Module updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
