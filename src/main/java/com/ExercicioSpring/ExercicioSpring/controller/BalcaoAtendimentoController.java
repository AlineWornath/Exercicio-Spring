package com.ExercicioSpring.ExercicioSpring.controller;

import com.ExercicioSpring.ExercicioSpring.dto.BalcaoAtendimentoDto;
import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import com.ExercicioSpring.ExercicioSpring.service.BalcaoAtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balcoes")
public class BalcaoAtendimentoController {

    @Autowired
    BalcaoAtendimentoService balcaoService;

    @GetMapping
    public List<BalcaoAtendimento> listar(){
        return balcaoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<BalcaoAtendimento> criarBalcaoAtendimento(@RequestBody BalcaoAtendimentoDto balcaoAtendimentoDto){
        BalcaoAtendimento balcaoAtendimento = balcaoService.criarBalcaoAtendimento(balcaoAtendimentoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(balcaoAtendimento);
    }

    @GetMapping("/{id}/relatorios")
    public ResponseEntity<String> gerarRelatorioBalcao(@PathVariable String id){
        String relatorio = balcaoService.gerarRelatorioBalcao(id);
        return ResponseEntity.status(HttpStatus.OK).body(relatorio);
    }

}
