package com.ExercicioSpring.ExercicioSpring.service;

import com.ExercicioSpring.ExercicioSpring.dto.RelatorioChamadoDto;
import com.ExercicioSpring.ExercicioSpring.entity.Atendente;
import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import com.ExercicioSpring.ExercicioSpring.entity.Chamado;
import com.ExercicioSpring.ExercicioSpring.repository.AtendenteRepository;
import com.ExercicioSpring.ExercicioSpring.repository.BalcaoAtendimentoRepository;
import com.ExercicioSpring.ExercicioSpring.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    @Autowired
    ChamadoRepository chamadoRepository;

    @Autowired
    AtendenteRepository atendenteRepository;

    @Autowired
    BalcaoAtendimentoRepository balcaoRepository;


    public Chamado criarChamado(String nomeCliente, String atendenteId, String balcaoId, String nomeProduto){
        Atendente atendente = atendenteRepository.findById(atendenteId).
                orElseThrow(() -> new RuntimeException("Atendente não encontrado!"));

        BalcaoAtendimento balcao = balcaoRepository.findById(balcaoId).
                orElseThrow(() -> new RuntimeException("Balcão não encontrado!"));

        Chamado chamado = new Chamado(nomeCliente, nomeProduto, atendente, balcao);
        return chamadoRepository.save(chamado);

    }

    public Chamado buscarChamadoPorId(String id) {
        return chamadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado!"));
    }

    public List<Chamado> listarChamados() {
        return chamadoRepository.findAll();
    }

    public List<RelatorioChamadoDto> relatorioChamados() {
        return chamadoRepository.findAll().
                stream().map(chamado -> new RelatorioChamadoDto(chamado.getNomeProduto(),
                        chamado.getEstadoChamado())).collect(Collectors.toList());
    }
}
