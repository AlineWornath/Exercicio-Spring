package com.ExercicioSpring.ExercicioSpring.dto;

public class AtendenteDto {
    private String nomeUsuario;
    private String balcaoId;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getBalcaoId() {
        return balcaoId;
    }

    public void setBalcaoId(String balcaoId) {
        this.balcaoId = balcaoId;
    }
}
