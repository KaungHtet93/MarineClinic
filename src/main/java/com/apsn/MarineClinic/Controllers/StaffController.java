package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Service.MedicalStaffService;
import com.apsn.MarineClinic.dto.Input.MedicalStaffInput;
import com.apsn.MarineClinic.dto.Input.PackageInput;
import com.apsn.MarineClinic.dto.response.MedicalStaffResponse;
import com.apsn.MarineClinic.dto.response.PackageResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private MedicalStaffService service;
    @PostMapping("/save")
    public MedicalStaffResponse saveStaff(@RequestBody MedicalStaffInput input) {
        return service.saveMedicalStaff(input);
    }

    @GetMapping("/list")
    public List<MedicalStaffResponse> getAllPackage() {
        return service.getAllMedicalStaff();
    }

    @GetMapping("{id}")
    public MedicalStaffResponse findById(@PathVariable(value = "id") Long id) {
        return service.getMedicalStaffById(id);
    }

    @PutMapping("/update/{id}")
    public MedicalStaffResponse updateMedical(@PathVariable Long id, @RequestBody MedicalStaffInput input) {
        return service.updateMedicalStaff(id, input);
    }

    @GetMapping("/searchName")
    public List<MedicalStaffResponse> getMedicalByName(@RequestParam(value = "name") String name) {
        return service.getByName(name);
    }
    @GetMapping("/searchDoctor")
    public List<MedicalStaffResponse> getDoctor() {
        return service.findDoctor();
    }
    @GetMapping("/searchQualification")
    public List<MedicalStaffResponse> getStaffByQualification(@RequestParam(value = "qualification") String qualification) {
        return service.findStaffByQualification(qualification);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteStaff(@PathVariable Long id) {
        return service.deleteById(id);
    }
}
