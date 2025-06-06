package com.ExercicioSpring.ExercicioSpring.entity;

import com.ExercicioSpring.ExercicioSpring.enums.EstadoChamado;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chamado")
public class Chamado implements Relatorio{
    @Id
    @Column(name = "chamado_id", columnDefinition = "CHAR(36)")
    private String chamadoId = UUID.randomUUID().toString();
    private String nomeCliente;
    private String nomeProduto;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_chamado", length = 20)
    private EstadoChamado estadoChamado;

    private LocalDateTime dataHoraCriacao;

    @ManyToOne
    @JoinColumn(name = "atendente_id")
    private Atendente atendente;

    @ManyToOne
    @JoinColumn(name = "balcao_id")
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

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    @Override
    public String gerarRelatorio() {
        return "Produto: " + nomeProduto + " Estado: " + estadoChamado;
    }

}
