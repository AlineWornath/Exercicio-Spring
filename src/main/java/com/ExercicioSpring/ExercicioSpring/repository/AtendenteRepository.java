package com.ExercicioSpring.ExercicioSpring.repository;

import com.ExercicioSpring.ExercicioSpring.entity.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendenteRepository extends JpaRepository<Atendente, String> {
}
