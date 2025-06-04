package com.ExercicioSpring.ExercicioSpring.repository;

import com.ExercicioSpring.ExercicioSpring.entity.Chamado;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ChamadoRepository {
    private List<Chamado> chamados = new ArrayList<>();

    public List<Chamado> listarTodos() {
        return new ArrayList<>(chamados);
    }

    public Chamado salvar(Chamado chamado) {
        chamados.add(chamado);
        return chamado;
    }

    public Optional<Chamado> buscarPorId(String id) {
        return chamados.stream().filter(chamado -> chamado.getChamadoId().equals(id)).findFirst();
    }
}
