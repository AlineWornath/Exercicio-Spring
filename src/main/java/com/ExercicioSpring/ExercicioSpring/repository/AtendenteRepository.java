package com.ExercicioSpring.ExercicioSpring.repository;

import com.ExercicioSpring.ExercicioSpring.entity.Atendente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AtendenteRepository {
    private List<Atendente> atendentes = new ArrayList<>();

    public List<Atendente> findAll() {
        return new ArrayList<>(atendentes);
    }

    public Atendente save(Atendente atendente) {
        atendentes.add(atendente);
        return atendente;
    }

    public Optional<Atendente> findById(String id) {
        return atendentes.stream().filter(atendente -> atendente.getAtendenteId().equals(id)).findFirst();
    }
}
