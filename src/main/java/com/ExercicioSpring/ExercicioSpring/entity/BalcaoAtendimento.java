package com.ExercicioSpring.ExercicioSpring.entity;

import jakarta.persistence.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Entity
public class BalcaoAtendimento implements Relatorio{
    @Id
    private String balcaoId = UUID.randomUUID().toString();
    private String nomeLoja;

    @OneToMany(mappedBy = "balcao")
    private List<Atendente> atendentes;

    @OneToMany(mappedBy = "balcao")
    private List<Chamado> chamados;

    public BalcaoAtendimento() {

    }

    public BalcaoAtendimento( String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }


    public String getId() {
        return balcaoId;
    }

    public void setId(String id) {
        this.balcaoId = id;
    }


    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }



    public String getBalcaoId() {
        return balcaoId;
    }

    public void setBalcaoId(String balcaoId) {
        this.balcaoId = balcaoId;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public List<Atendente> getAtendentes() {
        return atendentes;
    }

    public void setAtendentes(List<Atendente> atendentes) {
        this.atendentes = atendentes;
    }

    @Override
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Nome da loja: ").append(nomeLoja).append("\n");
        relatorio.append("Quantidade de atendimentos: ").append(chamados.size()).append("\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Chamado chamado : chamados) {
            String dataHora = chamado.getDataHoraCriacao().format(formatter);
            relatorio.append("Atendente: ").append(chamado.getAtendente().getNomeUsuario()).append("\n")
                    .append(dataHora).append("\n");
        }
        return relatorio.toString();
    }

}
