package com.helpdesksenai.helpdesksenai.chamado;

import com.helpdesksenai.helpdesksenai.cliente.Cliente;
import com.helpdesksenai.helpdesksenai.cliente.ClienteDTO;
import com.helpdesksenai.helpdesksenai.cliente.ClienteService;
import com.helpdesksenai.helpdesksenai.enums.PrioridadeEnum;
import com.helpdesksenai.helpdesksenai.enums.StatusEnum;
import com.helpdesksenai.helpdesksenai.exceptions.ObjectNotFoundException;
import com.helpdesksenai.helpdesksenai.tecnico.Tecnico;
import com.helpdesksenai.helpdesksenai.tecnico.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    private final String OBJETO_NAO_ENCONTRADO = "objeto não encontrado ";
    private final String POSSUI_CHAMADO_EM_ABERTO = " A entidade Possui chamado em Aberto e não pode ser excluido";
    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO + id));
    }
    public List<Chamado> findAll() {
        return repository.findAll();
    }
    public Chamado create(ChamadoDTO objDTO) {
        objDTO.setId(null);
        return repository.save(novoChamado(objDTO));
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
        chamado.setPrioridadeEnum(PrioridadeEnum.toEnum(chamadoDTO.getPrioridade().getCodigo()));
        chamado.setStatusEnum(StatusEnum.toEnum(chamadoDTO.getStatus().getCodigo()));
        chamado.setTitulo(chamadoDTO.getTitulo());
        chamado.setObservacoes(chamadoDTO.getObservacoes());
        return chamado;
    }
}
