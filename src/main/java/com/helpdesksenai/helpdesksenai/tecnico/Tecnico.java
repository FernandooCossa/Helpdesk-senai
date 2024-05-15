package com.helpdesksenai.helpdesksenai.tecnico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesksenai.helpdesksenai.chamado.Chamado;
import com.helpdesksenai.helpdesksenai.enums.PerfilEnum;
import com.helpdesksenai.helpdesksenai.pessoa.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Tecnico extends Pessoa {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    List<Chamado> chamados = new ArrayList<>();

    public Tecnico(TecnicoDTO tecnicoDTO) {
        super(
                tecnicoDTO.getId(),
                tecnicoDTO.getNome(),
                tecnicoDTO.getCpf(),
                tecnicoDTO.getEmail(),
                tecnicoDTO.getSenha());
                tecnicoDTO.getDataCriacao();
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
