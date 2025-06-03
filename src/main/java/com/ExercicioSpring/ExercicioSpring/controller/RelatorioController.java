package com.ExercicioSpring.ExercicioSpring.controller;

import com.ExercicioSpring.ExercicioSpring.dto.RelatorioBalcaoDto;
import com.ExercicioSpring.ExercicioSpring.dto.RelatorioChamadoDto;
import com.ExercicioSpring.ExercicioSpring.service.BalcaoAtendimentoService;
import com.ExercicioSpring.ExercicioSpring.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private BalcaoAtendimentoService balcaoAtendimentoService;

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping("/balcao/{id}")
    public List<RelatorioBalcaoDto> relatorioBalcao(@PathVariable String id){
        return balcaoAtendimentoService.relatorioBalcaoAtendimento(id);
    }

    @GetMapping("/chamados")
    public List<RelatorioChamadoDto> relatorioChamados() {
        return chamadoService.relatorioChamados();
    }
}
