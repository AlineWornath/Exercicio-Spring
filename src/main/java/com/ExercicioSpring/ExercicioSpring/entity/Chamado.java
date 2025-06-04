package com.ExercicioSpring.ExercicioSpring.entity;

import com.ExercicioSpring.ExercicioSpring.enums.EstadoChamado;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Chamado{
    @Id
    private String chamadoId = UUID.randomUUID().toString();
    private String nomeCliente;
    private String nomeProduto;
    private EstadoChamado estadoChamado;
    private LocalDateTime dataHoraCriacao;

    @ManyToOne
    private Atendente atendente;

    @ManyToOne
    private BalcaoAtendimento balcao;


    public Chamado() {

    }

    public Chamado(String nomeCliente, String nomeProduto, Atendente atendente, BalcaoAtendimento balcao) {
        this.nomeCliente = nomeCliente;
        this.nomeProduto = nomeProduto;
        this.atendente = atendente;
        this.balcao = balcao;
        this.estadoChamado = EstadoChamado.ABERTO;
        this.dataHoraCriacao = LocalDateTime.now();

    }

    public BalcaoAtendimento getBalcao() {
        return balcao;
    }

    public void setBalcao(BalcaoAtendimento balcao) {
        this.balcao = balcao;
    }

    public String getChamadoId() {
        return chamadoId;
    }

    public void setChamadoId(String chamadoId) {
        this.chamadoId = chamadoId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
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

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public String gerarRelatorio(){
        String relatorio = "Produto: " + nomeProduto + "\nEstado do chamado: " + estadoChamado;
        return relatorio;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }
}
