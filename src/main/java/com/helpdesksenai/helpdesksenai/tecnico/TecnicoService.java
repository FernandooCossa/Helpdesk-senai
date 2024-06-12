package com.helpdesksenai.helpdesksenai.tecnico;

import com.helpdesksenai.helpdesksenai.exceptions.ObjectNotFoundException;
import com.helpdesksenai.helpdesksenai.pessoa.Pessoa;
import com.helpdesksenai.helpdesksenai.pessoa.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    private final String OBJETO_NAO_ENCONTRADO = "objeto não encontrado ";
    private final String POSSUI_CHAMADO_EM_ABERTO = " A entidade Possui chamado em Aberto e não pode ser excluido";
    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO + id));
    }
    public List<Tecnico> findAll() {
        return repository.findAll();
    }
    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return repository.save(newObj);
    }
    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        objDTO.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCpfEEmail(objDTO);
        oldObj = new Tecnico(objDTO);
        return repository.save(oldObj);
    }
    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getChamados() != null && obj.getChamados().size() > 0) { throw new DataIntegrityViolationException(POSSUI_CHAMADO_EM_ABERTO + id);}
        repository.deleteById(id);
    }
    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");}
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) { throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");}

    }
}
