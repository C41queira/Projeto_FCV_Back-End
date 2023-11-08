package com.projetofcv.rosangelaestetica.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetofcv.rosangelaestetica.entity.Work;
import com.projetofcv.rosangelaestetica.service.WorkService;

@RestController
@RequestMapping( value = "/works")
public class WorkResource {
    
    @Autowired
    private WorkService service;

    @GetMapping
    public ResponseEntity<List<Work>> findAll(){
        List<Work> list = service.findAll();  
        return ResponseEntity.ok().body(list); 
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Work> findByWork(@PathVariable Long id){
        Work u = service.findById(id);
        return ResponseEntity.ok().body(u); 
    }

    @PostMapping
    public ResponseEntity<Work> insert(@RequestBody Work obj){
        obj = service.insert(obj); 
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build(); 
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Work> update(@PathVariable Long id, @RequestBody Work obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj); 
    }
}
