package com.unisca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.unisca.entity.Estudiante;
import com.unisca.repository.EstudianteRepository;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiante-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("lista", estudianteRepository.findAll());
        return "estudiantes";
    }
}
