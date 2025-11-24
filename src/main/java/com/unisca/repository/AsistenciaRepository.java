package com.unisca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unisca.entity.Asistencia;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
}
