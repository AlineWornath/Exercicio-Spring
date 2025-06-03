package com.ExercicioSpring.ExercicioSpring.controller;

import com.ExercicioSpring.ExercicioSpring.dto.ChamadoDto;
import com.ExercicioSpring.ExercicioSpring.entity.Chamado;
import com.ExercicioSpring.ExercicioSpring.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    @Autowired
    ChamadoService chamadoService;

    @GetMapping
    public List<Chamado> listar() {
        return chamadoService.listarChamados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chamado> buscar(@PathVariable String id) {
       Chamado chamado = chamadoService.buscarChamadoPorId(id);
        return ResponseEntity.ok().body(chamado);
    }

    @PostMapping
    public ResponseEntity<Chamado> criarChamado(@RequestBody ChamadoDto chamadoDto) throws Throwable {
        Chamado chamado = chamadoService.criarChamado(
                chamadoDto.getNomeCliente(),
                chamadoDto.getAtendenteId(),
                chamadoDto.getBalcaoId(),
                chamadoDto.getNomeProduto());

        return ResponseEntity.status(201).body(chamado);
    }

}
