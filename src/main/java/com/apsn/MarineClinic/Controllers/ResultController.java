package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Service.ResultService;
import com.apsn.MarineClinic.dto.Input.CompanyInput;
import com.apsn.MarineClinic.dto.Input.ResultInput;
import com.apsn.MarineClinic.dto.response.CompanyResponse;
import com.apsn.MarineClinic.dto.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {
    @Autowired
    private ResultService service;

    //get all
    @GetMapping("/list")
    public List<ResultResponse> getAllResult() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResultResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public List<ResultResponse> getBySeamanName(@RequestParam(value = "name") String name) {
        return service.getBySeamanName(name);
    }

    @GetMapping("/between")
    public List<ResultResponse> getByDateBetween(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return service.getByDateBetween(startDate, endDate);
    }

    @PostMapping
    public ResultResponse save(@RequestBody ResultInput input) {
        return service.create(input);
    }

    @PutMapping("{id}")
    public ResultResponse update(@PathVariable Long id, @RequestBody ResultInput input) {
        return service.update(id, input);
    }

    @DeleteMapping("{id}")
    public Boolean delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

}
