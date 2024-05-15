package com.helpdesksenai.helpdesksenai.tecnico;

import com.helpdesksenai.helpdesksenai.pessoa.Pessoa;
import com.helpdesksenai.helpdesksenai.pessoa.PessoaRepository;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id, obj));
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
        if (!objDTO.getSenha().equals(oldObj.getSenha())) {objDTO.setSenha(objDTO.getSenha());}
        validaPorCpfEEmail(objDTO);
        oldObj = new Tecnico(objDTO);
        return repository.save(oldObj);
    }
    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getChamados().size() > 0) { throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");}
        repository.deleteById(id);
    }
    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");}
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) { throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");}

    }
}
