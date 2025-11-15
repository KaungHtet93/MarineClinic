package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Service.SeamanService;
import com.apsn.MarineClinic.dto.Input.CompanyInput;
import com.apsn.MarineClinic.dto.Input.SeamanInput;
import com.apsn.MarineClinic.dto.response.CompanyResponse;
import com.apsn.MarineClinic.dto.response.SeamanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seaman")
public class SeamanController {
    @Autowired

    private SeamanService service;
    @GetMapping("/list")
    public List<SeamanResponse> getAll() {
        return service.getAll();
    }
    @PostMapping
    public SeamanResponse create(@RequestBody SeamanInput input){
        return service.save(input);
    }
    @GetMapping("{id}")
    public SeamanResponse findById(@PathVariable(value = "id") Long id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    public SeamanResponse update(@PathVariable Long id, @RequestBody SeamanInput input) {
        return service.update(id, input);
    }
    @DeleteMapping("{id}")
    public Boolean delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/search")
    public List<SeamanResponse> search(
            @RequestParam String name,
            @RequestParam(defaultValue = "name") String filterBy) {

        return switch (filterBy.toLowerCase()) {
            case "cdc" -> service.getByCDCNo(name);
            case "rank" -> service.getByRank(name);
            default -> service.getByName(name);
        };
    }
    @GetMapping("/company/{id}")
    public List<SeamanResponse> getByCompanyId(@PathVariable Long id) {
        return service.getByCompany(id);
    }
}
