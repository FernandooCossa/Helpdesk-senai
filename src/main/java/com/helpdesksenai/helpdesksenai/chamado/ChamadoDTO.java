package com.helpdesksenai.helpdesksenai.chamado;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesksenai.helpdesksenai.cliente.Cliente;
import com.helpdesksenai.helpdesksenai.enums.PrioridadeEnum;
import com.helpdesksenai.helpdesksenai.enums.StatusEnum;
import com.helpdesksenai.helpdesksenai.tecnico.Tecnico;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class ChamadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    private PrioridadeEnum prioridade;
    private StatusEnum status;
    @NotNull(message = "O campo TITULO é requerido")
    private String titulo;
    @NotNull(message = "O campo OBSERVAÇÕES é requerido")
    private String observacoes;
    private Integer tecnico;
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO(){

    }

    public ChamadoDTO(Chamado chamado){
        this.id = chamado.getId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamento = chamado.getDataFechamento();
        this.prioridade = chamado.getPrioridadeEnum();
        this.status = chamado.getStatusEnum();
        this.titulo = chamado.getTitulo();
        this.observacoes = chamado.getObservacoes();
        this.tecnico = chamado.getTecnico().getId();
        this.cliente = chamado.getCliente().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.nomeCliente = chamado.getCliente().getNome();
    }

//    public ChamadoDTO(Integer id, LocalDate dataAbertura, LocalDate dataFechamento, Integer prioridade, Integer status, String titulo, String observacoes, Integer tecnico, Integer cliente, String nomeTecnico, String nomeCliente) {
//        this.id = id;
//        this.dataAbertura = dataAbertura;
//        this.dataFechamento = dataFechamento;
//        this.prioridade = prioridade;
//        this.status = status;
//        this.titulo = titulo;
//        this.observacoes = observacoes;
//        this.tecnico = tecnico;
//        this.cliente = cliente;
//        this.nomeTecnico = nomeTecnico;
//        this.nomeCliente = nomeCliente;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public PrioridadeEnum getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeEnum prioridade) {
        this.prioridade = prioridade;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
