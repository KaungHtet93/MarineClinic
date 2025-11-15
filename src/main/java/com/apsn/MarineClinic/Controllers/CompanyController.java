package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Service.CompanyService;
import com.apsn.MarineClinic.dto.Input.CompanyInput;
import com.apsn.MarineClinic.dto.Input.DiseaseInput;
import com.apsn.MarineClinic.dto.response.CompanyResponse;
import com.apsn.MarineClinic.dto.response.DiseaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService service;
    @PostMapping
    public CompanyResponse save(@RequestBody CompanyInput input) {
        return service.create(input);
    }

    @GetMapping("/list")
    public List<CompanyResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public CompanyResponse findById(@PathVariable(value = "id") Long id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    public CompanyResponse update(@PathVariable Long id, @RequestBody CompanyInput input) {
        return service.update(id, input);
    }
    @DeleteMapping("{id}")
    public Boolean delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/search")
    public List<CompanyResponse> getByName(@RequestParam(value = "name") String name) {
        return service.getByName(name);
    }
}
