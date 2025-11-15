package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.Company;
import com.apsn.MarineClinic.Repository.CompanyRepository;
import com.apsn.MarineClinic.dto.Input.CompanyInput;
import com.apsn.MarineClinic.dto.response.CompanyResponse;
import com.apsn.MarineClinic.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository repository;
    private CompanyMapper mapper;
    public CompanyService(CompanyMapper mapper){
        this.mapper=mapper;
    }
    //get all
    public List<CompanyResponse> getAll() {
        return mapper.toCompanyResponseList(repository.findAll()) ;
    }

    //get by name
    public List<CompanyResponse> getByName(String name) {
        return mapper.toCompanyResponseList(repository.findByName(name));
    }
    //create
    public CompanyResponse create(CompanyInput input){
        Company entity=new Company();
        entity.setName(input.name());
        entity.setEmail(input.email());
        entity.setAddress(input.address());
        repository.save(entity);
        return mapper.toCompanyResponse(entity);
    }
    //update
    public CompanyResponse update(Long id , CompanyInput input){
        Optional<Company> optional=repository.findById(id);
        if(optional.isPresent()){
            Company entity=optional.get();
            entity.setName(input.name());
            entity.setEmail(input.email());
            entity.setAddress(input.address());
            repository.save(entity);
            return mapper.toCompanyResponse(entity);
        }else throw new RuntimeException("Company not found");
    }


    public Boolean deleteById(Long id) {
        repository.deleteById(id);
        return true;
    }

    public CompanyResponse getById(Long id) {
        return mapper.toCompanyResponse(repository.findById(id).orElseThrow(()->new RuntimeException("Company not found with id: " + id)));
    }
}
