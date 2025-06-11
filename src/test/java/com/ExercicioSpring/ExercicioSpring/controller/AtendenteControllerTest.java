package com.ExercicioSpring.ExercicioSpring.controller;

import com.ExercicioSpring.ExercicioSpring.dto.AtendenteDto;
import com.ExercicioSpring.ExercicioSpring.entity.Atendente;
import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import com.ExercicioSpring.ExercicioSpring.service.AtendenteService;
import com.ExercicioSpring.ExercicioSpring.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtendenteController.class)
public class AtendenteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private AtendenteService atendenteService;

    @Test
    void quandoListarAtendentesDeveRetornarLista() throws Exception {
        Atendente atendente1 = new Atendente();
        atendente1.setNomeUsuario("Juan");
        Atendente atendente2 = new Atendente();
        atendente2.setNomeUsuario("Pedro");
        List<Atendente> atendentes = Arrays.asList(atendente1, atendente2);

        given(atendenteService.listarTodos()).willReturn(atendentes);

        mvc.perform(get("/atendentes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeUsuario").value("Juan"))
                .andExpect(jsonPath("$[1].nomeUsuario").value("Pedro"))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void quandoBuscarPorIdDeveRetornarAtendente() throws Exception {
        Atendente atendente = new Atendente();
        String id = "Teste123";
        atendente.setNomeUsuario("Pedro");
        atendente.setAtendenteId(id);

        given(atendenteService.buscarPorId(id)).willReturn(atendente);

        mvc.perform(get("/atendentes/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeUsuario").value("Pedro"))
                .andExpect(jsonPath("$.atendenteId").value("Teste123"));
    }

    @Test
    void quandoCriarAtendenteDeveRetornarCreated() throws Exception {
        BalcaoAtendimento balcaoAtendimento = new BalcaoAtendimento();
        balcaoAtendimento.setBalcaoId("Teste123");

        AtendenteDto atendenteDto = new AtendenteDto();
        atendenteDto.setNomeUsuario("Ana");
        atendenteDto.setBalcaoId(balcaoAtendimento.getBalcaoId());

        Atendente atendente = new Atendente();
        atendente.setNomeUsuario("Ana");
        atendente.setBalcao(balcaoAtendimento);

        given(atendenteService.criarAtendente(atendenteDto)).willReturn(atendente);

        mvc.perform(post("/atendentes")
        .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(atendenteDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeUsuario").value("Ana"))
                .andExpect(jsonPath("$.balcao.balcaoId").value("Teste123"));
    }
}
