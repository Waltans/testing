package com.urfu.testing.extern.controller;

import com.urfu.testing.application.services.ModuleService;
import com.urfu.testing.domain.Module;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Контроллер для взаимодействия с Thymeleaf
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("modules")
public class ModuleController {

    private final ModuleService moduleService;

    @GetMapping
    public String getAllModules(Model model) {
        try {
            List<Module> modules = moduleService.getAllModules();
            model.addAttribute("modules", modules);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "moduleList";
    }


    @GetMapping("/new")
    public String newModule(Model model) {

        Module module = new Module();
        model.addAttribute("modules", module);

        return "newModule";
    }

    @PostMapping("save")
    public String createModule(@ModelAttribute("module") Module module, RedirectAttributes redirectAttributes) {
        try {
            this.moduleService.saveModule(module);
            redirectAttributes.addFlashAttribute("message", "Saved successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/modules";

    }

    @GetMapping("/{uuid}/edit")
    public String editModule(Model model, @PathVariable UUID uuid) {
        try {
            Module module = moduleService.getModule(uuid).
                    orElseThrow(() -> new IllegalArgumentException("Invalid education program ID"));
            model.addAttribute("modules", module);
        } catch (Exception e) {
            model.addAttribute("massage", e);
        }
        return "newModule";
    }

    @DeleteMapping("/{uuid}/delete")
    public String deleteModule(@PathVariable UUID uuid) {
        try {
            System.out.println(uuid);
            moduleService.deleteModule(uuid);
        } catch (Exception e) {
            throw new NoSuchElementException("Не верный uuid");
        }

        return "moduleList";
    }
}
