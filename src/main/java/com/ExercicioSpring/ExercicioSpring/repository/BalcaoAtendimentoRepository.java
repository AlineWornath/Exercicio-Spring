package com.ExercicioSpring.ExercicioSpring.repository;

import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BalcaoAtendimentoRepository {
    private List<BalcaoAtendimento> balcoes = new ArrayList<>();

    public List<BalcaoAtendimento> findAll() {
        return new ArrayList<>(balcoes);
    }

    public BalcaoAtendimento save(BalcaoAtendimento balcaoAtendimento) {
        balcoes.add(balcaoAtendimento);
        return balcaoAtendimento;
    }

    public Optional<BalcaoAtendimento> findById(String id) {
        return balcoes.stream().filter(balcao -> balcao.getId().equals(id)).findFirst();
    }
}
