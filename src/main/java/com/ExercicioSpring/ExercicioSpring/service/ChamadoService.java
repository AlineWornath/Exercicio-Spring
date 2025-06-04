package com.ExercicioSpring.ExercicioSpring.service;

import com.ExercicioSpring.ExercicioSpring.dto.ChamadoDto;
import com.ExercicioSpring.ExercicioSpring.entity.Atendente;
import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import com.ExercicioSpring.ExercicioSpring.entity.Chamado;
import com.ExercicioSpring.ExercicioSpring.repository.AtendenteRepository;
import com.ExercicioSpring.ExercicioSpring.repository.BalcaoAtendimentoRepository;
import com.ExercicioSpring.ExercicioSpring.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChamadoService {

    @Autowired
    ChamadoRepository chamadoRepository;

    @Autowired
    AtendenteRepository atendenteRepository;

    @Autowired
    BalcaoAtendimentoRepository balcaoRepository;

    public List<Chamado> listarTodos() {
        return chamadoRepository.listarTodos();
    }

    public Chamado criarChamado(ChamadoDto chamadoDto) {
        Atendente atendente = atendenteRepository.buscarPorId(chamadoDto.getAtendenteId())
                .orElseThrow(() -> new RuntimeException("Atendente não encontrado!"));

        BalcaoAtendimento balcaoAtendimento = balcaoRepository.buscarPorId(chamadoDto.getBalcaoId())
                .orElseThrow(() -> new RuntimeException("Balcão não encontrado!"));

        Chamado chamado = new Chamado();
        chamado.setNomeCliente(chamadoDto.getNomeCliente());
        chamado.setNomeProduto(chamadoDto.getNomeProduto());
        chamado.setAtendente(atendente);
        chamado.setBalcao(balcaoAtendimento);
        chamado.setDataHoraCriacao(LocalDateTime.now());

        if (balcaoAtendimento.getChamados() == null) {
            balcaoAtendimento.setChamados(new ArrayList<>());
        }
        balcaoAtendimento.getChamados().add(chamado);

        return chamadoRepository.salvar(chamado);

    }

    public Chamado buscarPorId(String id) {
        return chamadoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado!"));
    }

    public String gerarRelatorioChamados() {
        List<Chamado> chamados = listarTodos();
        StringBuilder relatorioChamados = new StringBuilder();

        for (Chamado chamado : chamados) {
            relatorioChamados.append(chamado.gerarRelatorio()).append("\n");
        }
        return relatorioChamados.toString();
    }
}
