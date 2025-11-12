package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Model.PackageEntity;
import com.apsn.MarineClinic.Service.PackageService;
import com.apsn.MarineClinic.dto.Input.PackageInput;
import com.apsn.MarineClinic.dto.response.PackageResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/package")
public class PackageController {
    private PackageService service;
    @PostMapping("/save")
    public PackageResponse savePackage(@RequestBody PackageInput input) {
        return service.savePackage(input);
    }

    @GetMapping("/list")
    public List<PackageResponse> getAllPackage() {
        return service.getAllPackage();
    }

    @GetMapping("{id}")
    public PackageResponse findById(@PathVariable(value = "id") Long id) {
        return service.getPackageById(id);
    }

    @PutMapping("/update/{id}")
    public PackageResponse updatePackage(@PathVariable Long id, @RequestBody PackageInput input) {
        return service.updatePackage(id, input);
    }
    @DeleteMapping("/delete/{id}")
    public Boolean deleteCategory(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/search")
    public List<PackageResponse> getCategoryByName(@RequestParam(value = "name") String name) {
        return service.findPackageByName(name);
    }


    @GetMapping("{diseaseId}")
    public List<PackageResponse> getPackageByDisease(@PathVariable Long id){
        return service.getPackageByDisease(id);
    }
}
