package com.helpdesksenai.helpdesksenai.chamado;

import com.helpdesksenai.helpdesksenai.cliente.ClienteDTO;
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
@RequestMapping(value = "/chamados")
public class ChamadoResource {
    @Autowired
    private ChamadoService service;

    //POST
    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO){
        Chamado chamado = service.create(chamadoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).body(new ChamadoDTO(chamado));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado chamado = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> chamadoList = service.findAll();
        return ResponseEntity.ok().body(chamadoList.stream()
                .map(chamado -> new ChamadoDTO(chamado)).collect(Collectors.toList()));
    }

    //PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO chamadoDTO){
        Chamado chamado = service.update(id, chamadoDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

}
