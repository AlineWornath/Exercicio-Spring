package com.ExercicioSpring.ExercicioSpring.repository;

import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BalcaoAtendimentoRepository {
    private List<BalcaoAtendimento> balcoes = new ArrayList<>();

    public List<BalcaoAtendimento> listarTodos() {
        return new ArrayList<>(balcoes);
    }

    public BalcaoAtendimento salvar(BalcaoAtendimento balcaoAtendimento) {
        balcoes.add(balcaoAtendimento);
        return balcaoAtendimento;
    }

    public Optional<BalcaoAtendimento> buscarPorId(String id) {
        return balcoes.stream().filter(balcao -> balcao.getBalcaoId().equals(id)).findFirst();
    }
}
