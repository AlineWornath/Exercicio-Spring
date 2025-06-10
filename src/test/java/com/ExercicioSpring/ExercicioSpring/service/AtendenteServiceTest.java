package com.ExercicioSpring.ExercicioSpring.service;

import com.ExercicioSpring.ExercicioSpring.dto.AtendenteDto;
import com.ExercicioSpring.ExercicioSpring.entity.Atendente;
import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import com.ExercicioSpring.ExercicioSpring.repository.AtendenteRepository;
import com.ExercicioSpring.ExercicioSpring.repository.BalcaoAtendimentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AtendenteServiceTest {

    @Mock
    AtendenteRepository atendenteRepository;

    @Mock
    BalcaoAtendimentoRepository balcaoRepository;

    @InjectMocks
    AtendenteService atendenteService;

    @Test
    void deveListarAtendentes() {
        List<Atendente> atendentes = new ArrayList<>();

        Atendente atendente1 = new Atendente();
        atendente1.setNomeUsuario("Maria");
        Atendente atendente2 = new Atendente();
        atendente2.setNomeUsuario("Joao");

        atendentes.add(atendente1);
        atendentes.add(atendente2);

        Mockito.when(atendenteRepository.findAll()).thenReturn(atendentes);

        List<Atendente> resultado = atendenteService.listarTodos();

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals("Maria", resultado.get(0).getNomeUsuario());
        Assertions.assertEquals("Joao", resultado.get(1).getNomeUsuario());
    }

    @Test
    void deveCriarAtendentecomSucesso() {
        BalcaoAtendimento balcao = new BalcaoAtendimento();
        balcao.setBalcaoId("Teste123");
        Mockito.when(balcaoRepository.findById("Teste123")).thenReturn(Optional.of(balcao));

        AtendenteDto atendenteDto = new AtendenteDto();
        atendenteDto.setNomeUsuario("Maria");
        atendenteDto.setBalcaoId("Teste123");

        Atendente atendenteSalvo = new Atendente();
        atendenteSalvo.setNomeUsuario("Maria");
        atendenteSalvo.setNumeroMatricula("123456");
        atendenteSalvo.setBalcao(balcao);

        Mockito.when(atendenteRepository.save(any(Atendente.class))).thenReturn(atendenteSalvo);

        Atendente resultado = atendenteService.criarAtendente(atendenteDto);

        Assertions.assertEquals("Maria", resultado.getNomeUsuario());
        Assertions.assertEquals("123456", resultado.getNumeroMatricula());
        Assertions.assertEquals("Teste123", resultado.getBalcao().getBalcaoId());
    }

    @Test
    void deveLancarExcecaoQuandoCriarAtendenteComBalcaoInvalido() {
        AtendenteDto atendenteDto = new AtendenteDto();
        atendenteDto.setNomeUsuario("Maria");
        atendenteDto.setBalcaoId("balcaoInexistente");

        Mockito.when(balcaoRepository.findById("balcaoInexistente")).thenReturn(Optional.empty());

        RuntimeException erro = Assertions.assertThrows(RuntimeException.class,
                () -> atendenteService.criarAtendente(atendenteDto));
        Assertions.assertTrue(erro.getMessage().contains("Balcão não encontrado!"));
    }

    @Test
    void deveBuscarAtendentePorIdComSucesso() {
        Atendente atendente = new Atendente("12345", "Maria");

        Mockito.when(atendenteRepository.findById("12345")).thenReturn(Optional.of(atendente));

        Atendente resposta = atendenteService.buscarPorId("12345");

        Assertions.assertEquals("Maria", resposta.getNomeUsuario());
        Assertions.assertEquals("12345", resposta.getAtendenteId());
    }

    @Test
    void deveLancarExcecaoQuandoIdBuscadoNaoExiste() {
        Mockito.when(atendenteRepository.findById("idInexistente")).thenReturn(Optional.empty());

        RuntimeException erro = Assertions.assertThrows(RuntimeException.class,
                () -> atendenteService.buscarPorId("idInexistente"));
        Assertions.assertTrue(erro.getMessage().contains("Atendente não encontrado!"));
    }
}
