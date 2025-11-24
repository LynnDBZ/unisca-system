package com.unisca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.unisca.entity.Asistencia;
import com.unisca.entity.Estudiante;
import com.unisca.entity.Sesion;
import com.unisca.repository.AsistenciaRepository;
import com.unisca.repository.EstudianteRepository;
import com.unisca.repository.SesionRepository;

@Controller
@RequestMapping("/asistencia")
public class AsistenciaController {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @GetMapping("/registrar")
    public String registrarAsistencia(@RequestParam Long sesion, Model model) {

        model.addAttribute("sesionId", sesion);
        model.addAttribute("estudiante", new Estudiante());

        return "registrar-asistencia";
    }

    @PostMapping("/guardar")
    public String guardarAsistencia(@RequestParam Long sesionId,
                                    @ModelAttribute("estudiante") Estudiante estudianteTemp) {

        Estudiante estudiante = estudianteRepository.findByCodigo(estudianteTemp.getCodigo());

        if (estudiante == null) {
            estudiante = estudianteRepository.save(estudianteTemp);
        }

        Sesion sesion = sesionRepository.findById(sesionId).orElse(null);

        Asistencia a = new Asistencia();
        a.setEstudiante(estudiante);
        a.setSesion(sesion);
        asistenciaRepository.save(a);

        return "redirect:/asistencia/listar?sesion=" + sesionId;
    }

    @GetMapping("/listar")
    public String listar(@RequestParam Long sesion, Model model) {
        model.addAttribute("asistencias", asistenciaRepository.findAll());
        model.addAttribute("sesionId", sesion);
        return "asistencia-list";
    }
}

