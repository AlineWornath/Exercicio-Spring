package com.ExercicioSpring.ExercicioSpring.dto;

public class ChamadoDto {
    private String nomeCliente;
    private String atendenteId;
    private String nomeProduto;
    private String balcaoId;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getAtendenteId() {
        return atendenteId;
    }

    public void setAtendenteId(String atendenteId) {
        this.atendenteId = atendenteId;

    }
    public String getBalcaoId() {
        return balcaoId;
    }
    public void setBalcaoId(String balcaoId) {
        this.balcaoId = balcaoId;
    }
}


