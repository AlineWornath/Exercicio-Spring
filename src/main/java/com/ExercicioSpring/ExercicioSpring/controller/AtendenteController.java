package com.ExercicioSpring.ExercicioSpring.controller;

import com.ExercicioSpring.ExercicioSpring.dto.AtendenteDto;
import com.ExercicioSpring.ExercicioSpring.entity.Atendente;
import com.ExercicioSpring.ExercicioSpring.service.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atendentes")
public class AtendenteController {

    @Autowired
    private AtendenteService atendenteService;

    @GetMapping
    public List<Atendente> listar() {
        return atendenteService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Atendente> criarAtendente(@RequestBody AtendenteDto atendenteDto) {
        Atendente atendente =  atendenteService.criarAtendente(atendenteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(atendente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendente> buscarPorId(@PathVariable String id) {
        Atendente atendente = atendenteService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(atendente);
    }



}
