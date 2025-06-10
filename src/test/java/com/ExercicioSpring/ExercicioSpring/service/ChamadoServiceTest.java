package com.ExercicioSpring.ExercicioSpring.service;

import com.ExercicioSpring.ExercicioSpring.dto.ChamadoDto;
import com.ExercicioSpring.ExercicioSpring.entity.Atendente;
import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import com.ExercicioSpring.ExercicioSpring.entity.Chamado;
import com.ExercicioSpring.ExercicioSpring.enums.EstadoChamado;
import com.ExercicioSpring.ExercicioSpring.repository.AtendenteRepository;
import com.ExercicioSpring.ExercicioSpring.repository.BalcaoAtendimentoRepository;
import com.ExercicioSpring.ExercicioSpring.repository.ChamadoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ChamadoServiceTest {

    @Mock
    private ChamadoRepository chamadoRepository;

    @Mock
    private AtendenteRepository atendenteRepository;

    @Mock
    private BalcaoAtendimentoRepository balcaoRepository;

    @InjectMocks
    private ChamadoService chamadoService;

    @Test
    void deveListarTodosOsChamados() {
        Chamado chamado1 = new Chamado("Ana", "Café", null, null);
        Chamado chamado2 = new Chamado("José", "Chá", null, null);

        List<Chamado> chamados = List.of(chamado1, chamado2);

        Mockito.when(chamadoRepository.findAll()).thenReturn(chamados);

        List<Chamado> resultado = chamadoService.listarTodos();

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals("Ana", resultado.get(0).getNomeCliente());
        Assertions.assertEquals("Café", resultado.get(0).getNomeProduto());
        Assertions.assertEquals("José", resultado.get(1).getNomeCliente());
        Assertions.assertEquals("Chá", resultado.get(1).getNomeProduto());
    }

    @Test
    void deveCriarChamadoComSucesso() {
        Atendente atendente = new Atendente();
        atendente.setNomeUsuario("Maria");
        atendente.setAtendenteId("atendente123");

        BalcaoAtendimento balcao = new BalcaoAtendimento();
        balcao.setNomeLoja("Loja Central");
        balcao.setBalcaoId("balcao123");

        ChamadoDto chamadoDto = new ChamadoDto();
        chamadoDto.setNomeCliente("Ana");
        chamadoDto.setNomeProduto("Bolo de laranja");
        chamadoDto.setAtendenteId("atendente123");
        chamadoDto.setBalcaoId("balcao123");

        Mockito.when(atendenteRepository.findById("atendente123")).thenReturn(Optional.of(atendente));
        Mockito.when(balcaoRepository.findById("balcao123")).thenReturn(Optional.of(balcao));

        Chamado chamadoSalvo = new Chamado("Ana", "Bolo de laranja", atendente, balcao);
        chamadoSalvo.setChamadoId("chamado123");

        Mockito.when(chamadoRepository.save(any(Chamado.class))).thenReturn(chamadoSalvo);

        Chamado resultado = chamadoService.criarChamado(chamadoDto);

        Assertions.assertEquals("Ana", resultado.getNomeCliente());
        Assertions.assertEquals("Bolo de laranja", resultado.getNomeProduto());
        Assertions.assertEquals("chamado123", resultado.getChamadoId());
        Assertions.assertEquals("Maria", resultado.getAtendente().getNomeUsuario());
        Assertions.assertEquals("Loja Central", resultado.getBalcao().getNomeLoja());
    }

    @Test
    void deveLancarExcecaoAoCriarChamadoComAtendenteInvalido() {
        ChamadoDto chamadoDto = new ChamadoDto();
        chamadoDto.setNomeCliente("Ana");
        chamadoDto.setNomeProduto("Bolo de laranja");
        chamadoDto.setAtendenteId("atendenteInexistente");
        chamadoDto.setBalcaoId("balcao123");

        Mockito.when(atendenteRepository.findById("atendenteInexistente")).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> chamadoService.criarChamado(chamadoDto));
        Assertions.assertEquals("Atendente não encontrado!", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoAoCriarChamadoComBalcaoInvalido() {
        ChamadoDto chamadoDto = new ChamadoDto();
        chamadoDto.setNomeCliente("Joana");
        chamadoDto.setNomeProduto("Suco de laranja");
        chamadoDto.setAtendenteId("atendente123");
        chamadoDto.setBalcaoId("balcaoInexistente");

        Atendente atendente = new Atendente();
        atendente.setNomeUsuario("Joana");
        atendente.setAtendenteId("atendente123");
        Mockito.when(atendenteRepository.findById("atendente123")).thenReturn(Optional.of(atendente));

        Mockito.when(balcaoRepository.findById("balcaoInexistente")).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> chamadoService.criarChamado(chamadoDto));
        Assertions.assertEquals("Balcão não encontrado!", exception.getMessage());


    }

    @Test
    void deveBuscarChamadoPorIdComSucesso() {
        Chamado chamado = new Chamado("Ana", "Café", null, null);
        chamado.setChamadoId("chamado123");

        Mockito.when(chamadoRepository.findById("chamado123")).thenReturn(Optional.of(chamado));

        Chamado resultado = chamadoService.buscarPorId("chamado123");

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("chamado123", resultado.getChamadoId());
        Assertions.assertEquals("Ana", resultado.getNomeCliente());
        Assertions.assertEquals("Café", resultado.getNomeProduto());
    }

    @Test
    void deveLancarExcecaoQuandoIdBuscadoNaoExiste() {
        Mockito.when(chamadoRepository.findById("idInexistente")).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> {chamadoService.buscarPorId("idInexistente");});
        Assertions.assertEquals("Chamado não encontrado!", exception.getMessage());
    }

    @Test
    void deveGerarRelatorioComSucesso() {
        Chamado chamado1 = new Chamado();
        chamado1.setNomeProduto("Bolo de laranja");
        chamado1.setEstadoChamado(EstadoChamado.ABERTO);

        Chamado chamado2 = new Chamado();
        chamado2.setNomeProduto("Suco");
        chamado2.setEstadoChamado(EstadoChamado.EM_ATENDIMENTO);

        Mockito.when(chamadoRepository.findAll()).thenReturn(List.of(chamado1, chamado2));

        String relatorio = chamadoService.gerarRelatorioChamados();

        Assertions.assertTrue(relatorio.contains("Produto: Bolo de laranja Estado: ABERTO"));
        Assertions.assertTrue(relatorio.contains("Produto: Suco Estado: EM_ATENDIMENTO"));
    }
}
