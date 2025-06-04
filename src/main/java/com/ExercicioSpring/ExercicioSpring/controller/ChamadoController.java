package com.ExercicioSpring.ExercicioSpring.controller;

import com.ExercicioSpring.ExercicioSpring.dto.ChamadoDto;
import com.ExercicioSpring.ExercicioSpring.entity.Chamado;
import com.ExercicioSpring.ExercicioSpring.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    @Autowired
    ChamadoService chamadoService;

    @GetMapping
    public List<Chamado> listar() {
        return chamadoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chamado> buscar(@PathVariable String id) {
       Chamado chamado = chamadoService.buscarPorId(id);
        return ResponseEntity.ok().body(chamado);
    }

    @PostMapping
    public ResponseEntity<Chamado> criarChamado(@RequestBody ChamadoDto chamadoDto) throws Throwable {
        Chamado chamado = chamadoService.criarChamado(chamadoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(chamado);
    }

    @GetMapping("/relatorios")
    public ResponseEntity<String> gerarRelatorioChamados() {
        String relatorio = chamadoService.gerarRelatorioChamados();
        return ResponseEntity.status(HttpStatus.OK).body(relatorio);
    }
}
