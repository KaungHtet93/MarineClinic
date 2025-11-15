package com.apsn.MarineClinic.Controllers;
import com.apsn.MarineClinic.Model.Role;
import com.apsn.MarineClinic.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;

    // GET all roles
    @GetMapping
    public List<Role> getAll() {
        return service.getAll();
    }

    // GET role by ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Search by name
    @GetMapping("/search")
    public List<Role> findByName(@RequestParam String name) {
        return service.findByName(name);
    }

    // Create a new role
    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role input) {
        Role saved = service.create(input);
        return ResponseEntity.ok(saved);
    }

    // Update an existing role
    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable Long id, @RequestBody Role input) {
        try {
            Role updated = service.update(id, input);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
//    @DeleteMapping("/{id}")
//    public boolean deleteById(@PathVariable Long id){
//        service.de
//    }
}
