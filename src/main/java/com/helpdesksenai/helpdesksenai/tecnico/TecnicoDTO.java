package com.helpdesksenai.helpdesksenai.tecnico;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesksenai.helpdesksenai.enums.PerfilEnum;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotNull(message = "O campo NOME é requerido")
    private String nome;
    @NotNull(message = "O campo CPF é requerido")
    @CPF
    private String cpf;
    @NotNull(message = "O campo EMAIL é requerido")
    private  String email;
    @NotNull(message = "O campo SENHA é requerido")
    private String senha;
    private Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();
    public TecnicoDTO() {
        addPerfil(PerfilEnum.TECNICO);
    }

    public TecnicoDTO(Tecnico tecnico) {
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.cpf = tecnico.getCpf();
        this.email = tecnico.getEmail();
        this.senha = tecnico.getSenha();
        this.perfis = tecnico.getPerfis().stream().map(perfilEnum -> perfilEnum.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = tecnico.getDataCriacao();
        addPerfil(PerfilEnum.TECNICO);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<PerfilEnum> getPerfis() {
        return perfis.stream().map(x -> PerfilEnum.toEnum(x)).collect(Collectors.toSet());
    }
    public void addPerfil(PerfilEnum perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
