package com.helpdesksenai.helpdesksenai.tecnico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesksenai.helpdesksenai.chamado.Chamado;
import com.helpdesksenai.helpdesksenai.enums.PerfilEnum;
import com.helpdesksenai.helpdesksenai.pessoa.Pessoa;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Pessoa {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    List<Chamado> chamados = new ArrayList<>();

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(PerfilEnum.CLIENTE);
    }

    public Tecnico() {
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
