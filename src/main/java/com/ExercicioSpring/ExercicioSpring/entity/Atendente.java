package com.ExercicioSpring.ExercicioSpring.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "atendente")
public class Atendente {
    @Id
    @Column(name = "atendente_id", columnDefinition = "CHAR(36)")
    private String atendenteId = UUID.randomUUID().toString();
    private String nomeUsuario;
    private String numeroMatricula;

    @ManyToOne
    @JoinColumn(name = "balcao_id")
    private BalcaoAtendimento balcao;

    @OneToMany(mappedBy = "atendente")
    private List<Chamado> chamados;

    public Atendente() {

    }

    public Atendente(String nomeUsuario, BalcaoAtendimento balcao) {
        this.nomeUsuario = nomeUsuario;
        this.numeroMatricula = gerarMatricula();
        this.balcao = balcao;
    }

    public String getAtendenteId() {
        return atendenteId;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String gerarMatricula(){
        Random aleatorio = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            stringBuilder.append(aleatorio.nextInt(10));
        }
        String stringNumeros = stringBuilder.toString();

        this.numeroMatricula = "BR" + stringNumeros;
        return numeroMatricula;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public BalcaoAtendimento getBalcao() {
        return balcao;
    }

    public void setBalcao(BalcaoAtendimento balcao) {
        this.balcao = balcao;
    }

    public String getNome(){
        return this.nomeUsuario;
    }
}
