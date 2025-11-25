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

import java.time.LocalDateTime; // Necesitas esta importación para la hora de registro

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
                                    @ModelAttribute("estudiante") Estudiante estudianteForm) {

        Estudiante estudianteExistente = estudianteRepository.findByCodigo(estudianteForm.getCodigo());

        if (estudianteExistente == null) {
            estudianteExistente = estudianteRepository.save(estudianteForm);
        } else {
            estudianteExistente.setNombres(estudianteForm.getNombres());
            estudianteExistente.setApellidos(estudianteForm.getApellidos());
            estudianteExistente.setCarrera(estudianteForm.getCarrera());
            estudianteExistente.setCorreo(estudianteForm.getCorreo());
            estudianteRepository.save(estudianteExistente);
        }

        Sesion sesion = sesionRepository.findById(sesionId).orElse(null);

        Asistencia a = new Asistencia();
        a.setEstudiante(estudianteExistente);
        a.setSesion(sesion);
        a.setEstado("ACTIVA"); 
        a.setHoraRegistro(LocalDateTime.now()); 
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