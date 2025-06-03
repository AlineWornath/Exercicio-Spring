package com.ExercicioSpring.ExercicioSpring.repository;

import com.ExercicioSpring.ExercicioSpring.entity.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, String> {
}
