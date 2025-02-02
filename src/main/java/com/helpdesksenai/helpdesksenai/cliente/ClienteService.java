package com.helpdesksenai.helpdesksenai.cliente;

import com.helpdesksenai.helpdesksenai.exceptions.ObjectNotFoundException;
import com.helpdesksenai.helpdesksenai.pessoa.Pessoa;
import com.helpdesksenai.helpdesksenai.pessoa.PessoaRepository;
import com.helpdesksenai.helpdesksenai.tecnico.Tecnico;
import com.helpdesksenai.helpdesksenai.tecnico.TecnicoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final String OBJETO_NAO_ENCONTRADO = "objeto não encontrado ";
    private final String POSSUI_CHAMADO_EM_ABERTO = " A entidade Possui chamado em Aberto e não pode ser excluido";
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO + id));
    }
    public List<Cliente> findAll() {
        return repository.findAll();
    }
    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return repository.save(newObj);
    }
    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
        objDTO.setId(id);
        Cliente oldObj = findById(id);
        validaPorCpfEEmail(objDTO);
        oldObj = new Cliente(objDTO);
        return repository.save(oldObj);
    }
    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados() != null && obj.getChamados().size() > 0) { throw new DataIntegrityViolationException(POSSUI_CHAMADO_EM_ABERTO + id);}
        repository.deleteById(id);
    }
    private void validaPorCpfEEmail(ClienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");}
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) { throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");}

    }
}
