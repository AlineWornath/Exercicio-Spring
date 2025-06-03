package com.ExercicioSpring.ExercicioSpring.service;

import com.ExercicioSpring.ExercicioSpring.dto.RelatorioBalcaoDto;
import com.ExercicioSpring.ExercicioSpring.entity.Atendente;
import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import com.ExercicioSpring.ExercicioSpring.entity.Chamado;
import com.ExercicioSpring.ExercicioSpring.repository.BalcaoAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BalcaoAtendimentoService {

    @Autowired
    private BalcaoAtendimentoRepository balcaoAtendimentoRepository;

    public List<BalcaoAtendimento> listarTodos(){
        return balcaoAtendimentoRepository.findAll();
    }

    public List<RelatorioBalcaoDto> relatorioBalcaoAtendimento(String balcaoId) {
        BalcaoAtendimento balcaoAtendimento = buscarBalcaoAtendimento(balcaoId);

        String nomeLoja = balcaoAtendimento.getNomeLoja();

        return balcaoAtendimento.getAtendentes().stream()
                .map(atendente -> gerarRelatorioAtendente(atendente, nomeLoja, balcaoId))
                .collect(Collectors.toList());
    }

    private BalcaoAtendimento buscarBalcaoAtendimento(String balcaoId) {
        BalcaoAtendimento balcaoAtendimento = balcaoAtendimentoRepository.findById(balcaoId).
                orElseThrow(() -> new RuntimeException("Balcão não encontrado!"));
        return balcaoAtendimento;
    }

    private List<Chamado> filtrarChamados(Atendente atendente, String balcaoId) {
        return atendente.getChamados().
                    stream().filter(chamado -> (chamado.getBalcao()
                                .getBalcaoId().equals(balcaoId)))
                                .collect(Collectors.toList());
    }

    private RelatorioBalcaoDto gerarRelatorioAtendente(Atendente atendente, String nomeLoja, String balcaoId) {
        List<Chamado> atendimentos = filtrarChamados(atendente, balcaoId);

        int quantidadeAtendimentos = atendimentos.size();

        List<LocalDateTime> dataHoraAtendimentos = atendimentos.stream()
                .map(chamado -> chamado.getDataHoraCriacao())
                .collect(Collectors.toList());

        return new RelatorioBalcaoDto(atendente.getNomeUsuario(), nomeLoja, quantidadeAtendimentos, dataHoraAtendimentos);
    }
}
