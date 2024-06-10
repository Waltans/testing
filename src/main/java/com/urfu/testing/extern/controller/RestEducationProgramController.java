package com.urfu.testing.extern.controller;

import com.urfu.testing.application.services.EducationProgramService;
import com.urfu.testing.domain.EducationProgram;
import com.urfu.testing.domain.Head;
import com.urfu.testing.domain.Institute;
import com.urfu.testing.domain.Module;
import com.urfu.testing.extern.dto.EducationProgramDto;
import com.urfu.testing.extern.repository.HeadRepository;
import com.urfu.testing.extern.repository.InstituteRepository;
import com.urfu.testing.extern.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/educationPrograms")
public class RestEducationProgramController {

    private final EducationProgramService educationProgramService;
    private final HeadRepository headRepository;
    private final ModuleRepository moduleRepository;
    private final InstituteRepository instituteRepository;

    @GetMapping
    public ResponseEntity<List<EducationProgram>> getAll() {
        try {
            List<EducationProgram> educationPrograms = educationProgramService.findAll();

            return new ResponseEntity<>(educationPrograms, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveEducationProgram(@RequestBody EducationProgramDto educationProgramDto) {
        try {
            EducationProgram educationProgram = EducationProgram.builder()
                    .level(educationProgramDto.level())
                    .institute(instituteRepository.findById(educationProgramDto.institute())
                            .orElseThrow(() -> new IllegalArgumentException("institute not found")))
                    .head(headRepository.findById(educationProgramDto.head())
                            .orElseThrow(() -> new IllegalArgumentException("head not found")))
                    .title(educationProgramDto.title())
                    .accreditationTime(educationProgramDto.accreditationTime())
                    .cypher(educationProgramDto.cypher())
                    .standard(educationProgramDto.standard())
                    .module(moduleRepository.findById(educationProgramDto.module())
                            .orElseThrow(() -> new IllegalArgumentException("module not found")))
                    .build();
            educationProgramService.save(educationProgram);
            return ResponseEntity.status(HttpStatus.CREATED).body("Saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteEducationProgram(@PathVariable UUID uuid) {
        try {
            EducationProgram educationProgram = educationProgramService.findById(uuid)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid education program ID"));
            educationProgramService.delete(educationProgram.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("{uuid}")
    public ResponseEntity<String> updateEducationProgram(@PathVariable UUID uuid, @RequestBody EducationProgramDto educationProgramDto) {
        try {
            Institute institute = instituteRepository.findById(educationProgramDto.institute())
                    .orElseThrow(() -> new IllegalArgumentException("Institute not found"));
            Head head = headRepository.findById(educationProgramDto.head())
                    .orElseThrow(() -> new IllegalArgumentException("Head not found"));
            Module module = moduleRepository.findById(educationProgramDto.module())
                    .orElseThrow(() -> new IllegalArgumentException("Module not found"));
            educationProgramService.update(uuid, educationProgramDto.title(),
                    educationProgramDto.cypher(), educationProgramDto.level(),
                    educationProgramDto.standard(), institute,
                    head, educationProgramDto.accreditationTime(), module);
            return ResponseEntity.ok().body("Updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

