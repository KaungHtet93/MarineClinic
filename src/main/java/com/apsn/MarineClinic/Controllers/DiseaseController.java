package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Repository.DiseaseRepository;
import com.apsn.MarineClinic.Service.DiseaseService;
import com.apsn.MarineClinic.dto.Input.DiseaseInput;
import com.apsn.MarineClinic.dto.Input.PackageInput;
import com.apsn.MarineClinic.dto.response.DiseaseResponse;
import com.apsn.MarineClinic.dto.response.PackageResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/disease")

public class DiseaseController {
    private DiseaseService service;
    @PostMapping("/save")
    public DiseaseResponse saveDisease(@RequestBody DiseaseInput input) {
        return service.saveDisease(input);
    }

    @GetMapping("/list")
    public List<DiseaseResponse> getAllDisease() {
        return service.getAllDisease();
    }

    @GetMapping("{id}")
    public DiseaseResponse findById(@PathVariable(value = "id") Long id) {
        return service.getDiseaseById(id);
    }

    @PutMapping("/update/{id}")
    public DiseaseResponse updateDisease(@PathVariable Long id, @RequestBody DiseaseInput input) {
        return service.updateDisease(id, input);
    }
    @DeleteMapping("/delete/{id}")
    public Boolean deleteDisease(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/search")
    public List<DiseaseResponse> getDiseaseByName(@RequestParam(value = "name") String name) {
        return service.findDiseaseByName(name);
    }
}
