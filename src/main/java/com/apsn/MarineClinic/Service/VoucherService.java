package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.Voucher;
import com.apsn.MarineClinic.Repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    //get all
    @GetMapping("/voucherList")
    public List<Voucher> getAllVoucher(){
        return voucherRepository.findAll();
    }
    //create voucher

    //update voucher

    //delete
}
