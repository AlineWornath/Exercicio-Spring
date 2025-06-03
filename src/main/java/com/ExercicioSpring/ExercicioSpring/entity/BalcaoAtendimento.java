package com.ExercicioSpring.ExercicioSpring.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class BalcaoAtendimento {
    @Id
    private String balcaoId = UUID.randomUUID().toString();

    @OneToMany(mappedBy = "balcao")
    private List<Atendente> atendentes;

    @OneToMany(mappedBy = "balcao")
    private List<Chamado> chamados;

    private String nomeLoja;

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

}
