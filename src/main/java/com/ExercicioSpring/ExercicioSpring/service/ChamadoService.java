package com.ExercicioSpring.ExercicioSpring.service;

import com.ExercicioSpring.ExercicioSpring.dto.ChamadoDto;
import com.ExercicioSpring.ExercicioSpring.entity.Atendente;
import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import com.ExercicioSpring.ExercicioSpring.entity.Chamado;
import com.ExercicioSpring.ExercicioSpring.repository.AtendenteRepository;
import com.ExercicioSpring.ExercicioSpring.repository.BalcaoAtendimentoRepository;
import com.ExercicioSpring.ExercicioSpring.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private AtendenteRepository atendenteRepository;

    @Autowired
    private BalcaoAtendimentoRepository balcaoRepository;

    public List<Chamado> listarTodos() {
        return chamadoRepository.findAll();
    }

    public Chamado criarChamado(ChamadoDto chamadoDto) {
        Atendente atendente = atendenteRepository.findById(chamadoDto.getAtendenteId())
                .orElseThrow(() -> new RuntimeException("Atendente não encontrado!"));

        BalcaoAtendimento balcaoAtendimento = balcaoRepository.findById(chamadoDto.getBalcaoId())
                .orElseThrow(() -> new RuntimeException("Balcão não encontrado!"));

        Chamado chamado = new Chamado(chamadoDto.getNomeCliente(), chamadoDto.getNomeProduto(), atendente, balcaoAtendimento);

        return chamadoRepository.save(chamado);
    }

    public Chamado buscarPorId(String id) {
        return chamadoRepository.findById(id)
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
