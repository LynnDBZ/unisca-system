package com.unisca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unisca.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    Estudiante findByCodigo(String codigo);
}

