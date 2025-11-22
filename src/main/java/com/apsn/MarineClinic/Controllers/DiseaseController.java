package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Service.DiseaseService;
import com.apsn.MarineClinic.dto.Input.DiseaseInput;
import com.apsn.MarineClinic.dto.response.DiseaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/disease")
@RestController
public class DiseaseController {
    @Autowired
    private DiseaseService service;
    @PostMapping
    public DiseaseResponse save(@RequestBody DiseaseInput input) {
        return service.save(input);
    }

    @GetMapping("/list")
    public List<DiseaseResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public DiseaseResponse findById(@PathVariable(value = "id") Long id) {
        return service.getDiseaseById(id);
    }

    @PutMapping("{id}")
    public DiseaseResponse update(@PathVariable Long id, @RequestBody DiseaseInput input) {
        return service.update(id, input);
    }
    @DeleteMapping("{id}")
    public Boolean delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/search")
    public List<DiseaseResponse> getByName(@RequestParam(value = "name") String name) {
        return service.getByName(name);
    }
}
