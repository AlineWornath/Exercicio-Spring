package com.ExercicioSpring.ExercicioSpring.service;

import com.ExercicioSpring.ExercicioSpring.dto.BalcaoAtendimentoDto;
import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import com.ExercicioSpring.ExercicioSpring.repository.BalcaoAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalcaoAtendimentoService {

    @Autowired
    private BalcaoAtendimentoRepository balcaoAtendimentoRepository;

    public List<BalcaoAtendimento> listarTodos(){
        return balcaoAtendimentoRepository.findAll();
    }

    public BalcaoAtendimento criarBalcaoAtendimento(BalcaoAtendimentoDto balcaoAtendimentoDto){
        BalcaoAtendimento balcaoAtendimento = new BalcaoAtendimento();
        balcaoAtendimento.setNomeLoja(balcaoAtendimentoDto.getNomeLoja());

        return balcaoAtendimentoRepository.save(balcaoAtendimento);
    }

    public BalcaoAtendimento buscarPorId(String balcaoId) {
        BalcaoAtendimento balcaoAtendimento = balcaoAtendimentoRepository.findById(balcaoId).
                orElseThrow(() -> new RuntimeException("Balcão não encontrado!"));
        return balcaoAtendimento;
    }

    public String gerarRelatorioBalcao(String balcaoId) {
        BalcaoAtendimento balcao = buscarPorId(balcaoId);
        return balcao.gerarRelatorio();
    }
}
