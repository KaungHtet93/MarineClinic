package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Model.UserType;
import com.apsn.MarineClinic.Service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usertype")
public class UserTypeController {

    @Autowired
    private UserTypeService service;

    // GET all user types
    @GetMapping
    public List<UserType> getAll() {
        return service.getAll();
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserType> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Search by name
    @GetMapping("/search")
    public List<UserType> search(@RequestParam String name) {
        return service.findByName(name);
    }

    // Create
    @PostMapping
    public UserType create(@RequestBody UserType input) {
        return service.saveUser(input);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<UserType> update(
            @PathVariable Long id,
            @RequestBody UserType input) {
        try {
            return ResponseEntity.ok(service.update(id, input));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
