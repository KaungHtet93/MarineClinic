package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Service.ResultService;
import com.apsn.MarineClinic.Service.VoucherService;
import com.apsn.MarineClinic.dto.Input.ResultInput;
import com.apsn.MarineClinic.dto.Input.VoucherInput;
import com.apsn.MarineClinic.dto.response.ResultResponse;
import com.apsn.MarineClinic.dto.response.VoucherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService service;

    //get all
    @GetMapping("/list")
    public List<VoucherResponse> getAllResult() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<VoucherResponse> getByName(@RequestParam("name") String name, @RequestParam(defaultValue = "staff") String filterBy) {
        if(filterBy.equalsIgnoreCase("seaman")){
            return service.getBySeamanName(name);
        }
        return service.getByStaffName(name);
    }

    @GetMapping("/{id}")
    public VoucherResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/between")
    public List<VoucherResponse> getByDateBetween(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return service.getVoucherBetween(startDate, endDate);
    }

    @PostMapping
    public VoucherResponse save(@RequestBody VoucherInput input) {
        return service.create(input);
    }

    @PutMapping("{id}")
    public VoucherResponse update(@PathVariable Long id, @RequestBody VoucherInput input) {
        return service.update(id, input);
    }

    @DeleteMapping("{id}")
    public Boolean delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

}
