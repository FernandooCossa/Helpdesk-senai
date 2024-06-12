package com.helpdesksenai.helpdesksenai.cliente;

import com.helpdesksenai.helpdesksenai.tecnico.Tecnico;
import com.helpdesksenai.helpdesksenai.tecnico.TecnicoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService service;

    //POST
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = service.create(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDTO(cliente));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente cliente = service.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> clienteList = service.findAll();
        return ResponseEntity.ok().body(clienteList.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList()));
    }

    //PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = service.update(id, clienteDTO);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    //DELETE
    @DeleteMapping(value = "/{id}" )
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
