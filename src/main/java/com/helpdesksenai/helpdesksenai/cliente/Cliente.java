package com.helpdesksenai.helpdesksenai.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesksenai.helpdesksenai.chamado.Chamado;
import com.helpdesksenai.helpdesksenai.enums.PerfilEnum;
import com.helpdesksenai.helpdesksenai.pessoa.Pessoa;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    List<Chamado> chamados = new ArrayList<>();

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(PerfilEnum.CLIENTE);
    }

    public Cliente() {
        super();
        addPerfil(PerfilEnum.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
