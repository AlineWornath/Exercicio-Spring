package com.ExercicioSpring.ExercicioSpring.service;

import com.ExercicioSpring.ExercicioSpring.dto.AtendenteDto;
import com.ExercicioSpring.ExercicioSpring.entity.Atendente;
import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import com.ExercicioSpring.ExercicioSpring.repository.AtendenteRepository;
import com.ExercicioSpring.ExercicioSpring.repository.BalcaoAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendenteService {

    @Autowired
    private AtendenteRepository atendenteRepository;

    @Autowired
    private BalcaoAtendimentoRepository balcaoRepository;

    public List<Atendente> listarTodos() {
        return atendenteRepository.findAll();
    }

    public Atendente criarAtendente(AtendenteDto atendenteDto) {
        Atendente atendente = new Atendente();
        atendente.setNomeUsuario(atendenteDto.getNomeUsuario());
        if (atendenteDto.getBalcaoId() != null) {
            BalcaoAtendimento balcao = balcaoRepository.findById(atendenteDto.getBalcaoId())
                    .orElseThrow(() -> new RuntimeException("Balcão não encontrado!"));
            atendente.setBalcao(balcao);
        }
        atendente.setNumeroMatricula(atendente.gerarMatricula());
        return atendenteRepository.save(atendente);
    }

    public Atendente buscarPorId(String atendenteId) {
        return atendenteRepository.findById(atendenteId).
                orElseThrow(() -> new RuntimeException("Atendente não encontrado!"));
    }
}
