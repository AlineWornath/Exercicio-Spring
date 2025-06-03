package com.ExercicioSpring.ExercicioSpring.dto;

import java.time.LocalDateTime;
import java.util.List;

public class RelatorioBalcaoDto {
    private String nomeAtendente;
    private String nomeLoja;
    private int quantidadeAtendimentos;
    private List<LocalDateTime> dataHoraAtendimentos;

    public RelatorioBalcaoDto(String nomeAtendente, String nomeLoja, int quantidadeAtendimentos, List<LocalDateTime> dataHoraAtendimentos) {
        this.nomeAtendente = nomeAtendente;
        this.nomeLoja = nomeLoja;
        this.quantidadeAtendimentos = quantidadeAtendimentos;
        this.dataHoraAtendimentos = dataHoraAtendimentos;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public int getQuantidadeAtendimentos() {
        return quantidadeAtendimentos;
    }

    public void setQuantidadeAtendimentos(int quantidadeAtendimentos) {
        this.quantidadeAtendimentos = quantidadeAtendimentos;
    }

    public List<LocalDateTime> getDataHoraAtendimentos() {
        return dataHoraAtendimentos;
    }

    public void setDataHoraAtendimentos(List<LocalDateTime> dataHoraAtendimentos) {
        this.dataHoraAtendimentos = dataHoraAtendimentos;
    }
}
