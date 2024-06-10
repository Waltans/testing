package com.urfu.testing.extern.controller;

import com.urfu.testing.application.services.EducationProgramService;
import com.urfu.testing.domain.EducationProgram;
import com.urfu.testing.domain.Head;
import com.urfu.testing.domain.Institute;
import com.urfu.testing.domain.Module;
import com.urfu.testing.extern.repository.HeadRepository;
import com.urfu.testing.extern.repository.InstituteRepository;
import com.urfu.testing.extern.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Контроллер для взаимодействия с thymeleaf
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("educationPrograms")
public class EducationProgramController {
    public final EducationProgramService educationProgramService;

    private final ModuleRepository moduleRepository;
    private final InstituteRepository instituteRepository;
    private final HeadRepository headRepository;


    @GetMapping
    public String getAllEducationProgram(Model model) {
        try {
            List<EducationProgram> educationPrograms = educationProgramService.findAll();
            model.addAttribute("educationProgram", educationPrograms);
        } catch (Exception e) {
            model.addAttribute("massage", e.getMessage());
        }

        return "educationProgramList";
    }

    @GetMapping("/new")
    public String newEducationProgram(Model model) {
        EducationProgram educationProgram = new EducationProgram();
        model.addAttribute("educationProgram", educationProgram);
        model.addAttribute("modules", moduleRepository.findAll());
        model.addAttribute("institutes", instituteRepository.findAll());
        model.addAttribute("heads", headRepository.findAll());
        return "newEducationProgram";
    }

    @GetMapping("/{uuid}/edit")
    public String editEducationProgram(Model model, @PathVariable UUID uuid) {

        try {
            EducationProgram educationProgram = educationProgramService.findById(uuid).
                    orElseThrow(() -> new IllegalArgumentException("Invalid education program ID"));


            model.addAttribute("modules", moduleRepository.findAll());
            model.addAttribute("institutes", instituteRepository.findAll());
            model.addAttribute("heads", headRepository.findAll());
            model.addAttribute("educationProgram", educationProgram);

        } catch (Exception e) {
            model.addAttribute("massage", e);
        }
        return "newEducationProgram";
    }

    /**
     * Метод исполняется из view newEducationProgram
     *
     * @param educationProgram
     * @param headId
     * @param moduleId
     * @param instituteId
     * @param redirectAttributes
     * @return - view Все образовательные программы
     */
    @PostMapping("/save")
    public String saveEducationProgram(
            @ModelAttribute("educationProgram") EducationProgram educationProgram,
            @RequestParam("head") UUID headId,
            @RequestParam("module") UUID moduleId,
            @RequestParam("institute") UUID instituteId,
            RedirectAttributes redirectAttributes) {

        try {
            System.out.println(headId);
            System.out.println(moduleId);
            System.out.println(instituteId);
            Head head = headRepository.findById(headId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid head ID"));
            Module module = moduleRepository.findById(moduleId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid module ID"));
            Institute institute = instituteRepository.findById(instituteId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid institute ID"));

            educationProgram.setHead(head);
            educationProgram.setModule(module);
            educationProgram.setInstitute(institute);

            educationProgramService.save(educationProgram);
            redirectAttributes.addFlashAttribute("message", "Saved successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/educationPrograms";

    }

    /**
     * Удаление образовательной программы
     *
     * @param uuid - uuid
     * @return - view
     */
    @DeleteMapping("/{uuid}/delete")
    public String deleteEducationProgram(@PathVariable UUID uuid) {
        try {
            educationProgramService.delete(uuid);
        } catch (Exception e) {
            throw new NoSuchElementException("Не верный uuid");
        }

        return "educationProgramList";
    }
}
