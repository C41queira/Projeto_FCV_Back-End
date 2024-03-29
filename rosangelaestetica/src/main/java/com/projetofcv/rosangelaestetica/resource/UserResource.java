package com.projetofcv.rosangelaestetica.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetofcv.rosangelaestetica.entity.LoginRequest;
import com.projetofcv.rosangelaestetica.entity.User;
import com.projetofcv.rosangelaestetica.service.UserService;

@RestController
@RequestMapping( value = "/users")
public class UserResource {
    
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();  
        return ResponseEntity.ok().body(list); 
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findByUser(@PathVariable int id){
        User u = service.findById(id);
        return ResponseEntity.ok().body(u); 
    }

    @PostMapping(value = "/cadastro")
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj); 
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest){ 
        return ResponseEntity.ok().body(service.loginUser(loginRequest.getName(), loginRequest.getPassword())); 
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.noContent().build(); 
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PutMapping(value="/{id}")
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody User obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj); 
    }
}
