package com.unisca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.unisca.entity.Sesion;
import com.unisca.repository.SesionRepository;

@Controller
@RequestMapping("/sesiones")
public class SesionController {

    @Autowired
    private SesionRepository sesionRepository;
    
    // Inyectamos la URL de Railway. Si no la encuentra (localmente), usa localhost.
    @Value("${APP_BASE_URL:http://localhost:8080}") 
    private String baseUrl; 

    @GetMapping("/crear")
    public String mostrarFormularioCrearSesion(Model model) {
        model.addAttribute("sesion", new Sesion());
        return "crear-sesion";
    }

    @PostMapping("/guardar")
    public String guardarSesion(@ModelAttribute Sesion sesion) {
        Sesion nueva = sesionRepository.save(sesion);

        // Redirige a la pantalla del QR
        return "redirect:/sesiones/qr/" + nueva.getId();
    }

    @GetMapping("/qr/{id}")
    public String mostrarQR(@PathVariable Long id, Model model) {
        
        // CONSTRUCCIÓN DE LA URL: Usamos la variable inyectada para generar el enlace
        String urlAsistencia = baseUrl + "/asistencia/registrar?sesion=" + id;

        model.addAttribute("qrUrl", "/qr?texto=" + urlAsistencia);
        model.addAttribute("urlTexto", urlAsistencia);

        return "qr-view";
    }
}