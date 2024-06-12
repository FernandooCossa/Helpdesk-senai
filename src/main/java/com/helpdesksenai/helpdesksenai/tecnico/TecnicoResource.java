package com.helpdesksenai.helpdesksenai.tecnico;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnico")
public class TecnicoResource {
    @Autowired
   private TecnicoService service;

    //POST
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico tecnico = service.create(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TecnicoDTO(tecnico));
    }
    //GET
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> tecnicoList = service.findAll();
        return ResponseEntity.ok().body(tecnicoList.stream().map(tecnico -> new TecnicoDTO(tecnico)).collect(Collectors.toList()));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico tecnico = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }
    //PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico tecnico = service.update(id, tecnicoDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }
    //DELETE
    @DeleteMapping(value = "/{id}" )
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
