package com.helpdesksenai.helpdesksenai.chamado;

import com.helpdesksenai.helpdesksenai.cliente.Cliente;
import com.helpdesksenai.helpdesksenai.cliente.ClienteDTO;
import com.helpdesksenai.helpdesksenai.cliente.ClienteService;
import com.helpdesksenai.helpdesksenai.enums.PrioridadeEnum;
import com.helpdesksenai.helpdesksenai.enums.StatusEnum;
import com.helpdesksenai.helpdesksenai.tecnico.Tecnico;
import com.helpdesksenai.helpdesksenai.tecnico.TecnicoService;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository repository;
    private TecnicoService tecnicoService;
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id, obj));
    }
    public List<Chamado> findAll() {
        return repository.findAll();
    }
    public Chamado create(ChamadoDTO objDTO) {
        objDTO.setId(null);
        Chamado newObj = novoChamado(objDTO);
        return repository.save(newObj);
    }
    public Chamado update(Integer id, @Valid ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(id);
        Chamado chamado = findById(id);
        chamado = novoChamado(chamadoDTO);
        return repository.save(chamado);

    }
    public Chamado novoChamado(ChamadoDTO chamadoDTO){
        Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDTO.getCliente());
        Chamado chamado = new Chamado();
        if (chamadoDTO.getId() != null){
            chamado.setId(chamadoDTO.getId());
        }
        if (chamadoDTO.getStatus().equals(2)){
            chamado.setDataFechamento(LocalDate.now());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridadeEnum(PrioridadeEnum.toEnum(chamadoDTO.getPrioridade()));
        chamado.setStatusEnum(StatusEnum.toEnum(chamadoDTO.getStatus()));
        chamado.setTitulo(chamado.getTitulo());
        chamado.setObservacoes(chamadoDTO.getObservacoes());
        return chamado;
    }
}
