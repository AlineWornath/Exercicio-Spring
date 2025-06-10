package com.ExercicioSpring.ExercicioSpring.service;

import com.ExercicioSpring.ExercicioSpring.dto.BalcaoAtendimentoDto;
import com.ExercicioSpring.ExercicioSpring.entity.Atendente;
import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import com.ExercicioSpring.ExercicioSpring.entity.Chamado;
import com.ExercicioSpring.ExercicioSpring.repository.BalcaoAtendimentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BalcaoAtendimentoServiceTest {

    @Mock
    private BalcaoAtendimentoRepository balcaoRepository;

    @InjectMocks
    private BalcaoAtendimentoService balcaoService;

    @Test
    void deveListarTodosOsBalcoes() {
        List<BalcaoAtendimento> balcoes = List.of(new BalcaoAtendimento("Loja Central"),
                new BalcaoAtendimento("Loja Sul"));

        Mockito.when(balcaoRepository.findAll()).thenReturn(balcoes);

        List<BalcaoAtendimento> resultado = balcaoService.listarTodos();

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals("Loja Central", resultado.get(0).getNomeLoja());
        Assertions.assertEquals("Loja Sul", resultado.get(1).getNomeLoja());
    }

    @Test
    void deveCriarBalcaoComSucesso() {
        BalcaoAtendimentoDto balcaoDto = new BalcaoAtendimentoDto();
        balcaoDto.setNomeLoja("Loja Central");

        BalcaoAtendimento balcaoSalvo = new BalcaoAtendimento();
        balcaoSalvo.setNomeLoja("Loja Central");
        balcaoSalvo.setBalcaoId("12345");

        Mockito.when(balcaoRepository.save(any(BalcaoAtendimento.class))).thenReturn(balcaoSalvo);

        BalcaoAtendimento resultado = balcaoService.criarBalcaoAtendimento(balcaoDto);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Loja Central", resultado.getNomeLoja());
        Assertions.assertEquals("12345", resultado.getBalcaoId());
    }

    @Test
    void deveBuscarBalcaoPorIdComSucesso() {
        BalcaoAtendimento balcao = new BalcaoAtendimento();
        balcao.setNomeLoja("Loja Central");
        balcao.setBalcaoId("12345");

        Mockito.when(balcaoRepository.findById("12345")).thenReturn(Optional.of(balcao));

        BalcaoAtendimento resultado = balcaoService.buscarPorId("12345");

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Loja Central", resultado.getNomeLoja());
        Assertions.assertEquals("12345", resultado.getBalcaoId());
    }

    @Test
    void deveLancarExcecaoQuandoIdBuscadoNaoExiste() {
        Mockito.when(balcaoRepository.findById("idInexistente")).thenReturn(Optional.empty());

        RuntimeException erro = Assertions.assertThrows(RuntimeException.class,
                () -> balcaoService.buscarPorId("idInexistente"));
        Assertions.assertEquals("Balcão não encontrado!", erro.getMessage());
    }

    @Test
    void deveGerarRelatorioComSucesso() {
        BalcaoAtendimento balcao = new BalcaoAtendimento();
        balcao.setNomeLoja("Loja Central");
        balcao.setBalcaoId("12345");

        Atendente atendente = new Atendente();
        atendente.setNomeUsuario("Maria");

        Chamado chamado = new Chamado();
        chamado.setAtendente(atendente);
        chamado.setDataHoraCriacao(LocalDateTime.of(2025, 6, 10, 0, 0));

        balcao.setChamados(List.of(chamado));

        Mockito.when(balcaoRepository.findById("12345")).thenReturn(Optional.of(balcao));

        String resultado = balcaoService.gerarRelatorioBalcao("12345");

        Assertions.assertTrue(resultado.contains("Loja Central"));
        Assertions.assertTrue(resultado.contains("Maria"));
        Assertions.assertTrue(resultado.contains("10/06/2025"));
        Assertions.assertTrue(resultado.contains("Quantidade de atendimentos: 1"));
    }
}
