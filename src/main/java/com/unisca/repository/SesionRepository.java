package com.unisca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unisca.entity.Sesion;

public interface SesionRepository extends JpaRepository<Sesion, Long> {
}

