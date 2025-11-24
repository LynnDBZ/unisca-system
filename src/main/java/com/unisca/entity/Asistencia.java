package com.unisca.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK estudiante
    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    // FK sesión
    @ManyToOne
    @JoinColumn(name = "sesion_id")
    private Sesion sesion;

    private LocalDateTime horaRegistro;

    private String estado;

    public Asistencia() {
        this.horaRegistro = LocalDateTime.now();
        this.estado = "PRESENTE";
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public LocalDateTime getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(LocalDateTime horaRegistro) {
        this.horaRegistro = horaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
