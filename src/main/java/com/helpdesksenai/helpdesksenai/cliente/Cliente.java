package com.helpdesksenai.helpdesksenai.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesksenai.helpdesksenai.chamado.Chamado;
import com.helpdesksenai.helpdesksenai.enums.PerfilEnum;
import com.helpdesksenai.helpdesksenai.pessoa.Pessoa;
import com.helpdesksenai.helpdesksenai.tecnico.TecnicoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    List<Chamado> chamados = new ArrayList<>();

    public Cliente(ClienteDTO clienteDTO) {
        super(
                clienteDTO.getId(),
                clienteDTO.getNome(),
                clienteDTO.getCpf(),
                clienteDTO.getEmail(),
                clienteDTO.getSenha());
        clienteDTO.getDataCriacao();
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
