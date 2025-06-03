package com.ExercicioSpring.ExercicioSpring.controller;

import com.ExercicioSpring.ExercicioSpring.entity.BalcaoAtendimento;
import com.ExercicioSpring.ExercicioSpring.service.BalcaoAtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<BalcaoAtendimento> criarBalcaoAtendimento(@RequestBody BalcaoAtendimento balcaoAtendimento){

    }

}
