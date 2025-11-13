package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.Company;
import com.apsn.MarineClinic.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    //get all
    @GetMapping("/companyList")
    public List<Company> getAllCompany(){
        return companyRepository.findAll();
    }
    //get by name
    @GetMapping("{name}")
    public List<Company> getByName(String name){
        return companyRepository.findByName(name);
    }
    //create

    //update

    //delete
}
