package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Service.PackageService;
import com.apsn.MarineClinic.dto.Input.PackageInput;
import com.apsn.MarineClinic.dto.response.PackageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/package")
public class PackageController {
    @Autowired
    private PackageService service;

    @PostMapping
    public PackageResponse save(@RequestBody PackageInput input) {
        return service.create(input);
    }

    @GetMapping("/list")
    public List<PackageResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public PackageResponse findById(@PathVariable(value = "id") Long id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    public PackageResponse update(@PathVariable Long id, @RequestBody PackageInput input) {
        return service.update(id, input);
    }

    @DeleteMapping("{id}")
    public Boolean delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/search")
    public List<PackageResponse> searchPackages(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "diseaseId", required = false) Long diseaseId) {
        if (name != null) {
            return service.findByName(name);
        } else if (diseaseId != null) {
            return service.findByDisease(diseaseId);
        } else {
            return service.getAll(); // fallback if no filters provided
        }
    }
}
