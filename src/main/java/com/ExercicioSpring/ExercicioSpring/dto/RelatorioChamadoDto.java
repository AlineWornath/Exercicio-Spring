package com.ExercicioSpring.ExercicioSpring.dto;

import com.ExercicioSpring.ExercicioSpring.Enums.EstadoChamado;

public class RelatorioChamadoDto {
    private String nomeProduto;
    private EstadoChamado estadoChamado;

    public RelatorioChamadoDto(String nomeProduto, EstadoChamado estadoChamado) {
        this.nomeProduto = nomeProduto;
        this.estadoChamado = estadoChamado;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public EstadoChamado getEstadoChamado() {
        return estadoChamado;
    }

    public void setEstadoChamado(EstadoChamado estadoChamado) {
        this.estadoChamado = estadoChamado;
    }
}
