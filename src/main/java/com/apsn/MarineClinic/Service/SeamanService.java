package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.Company;
import com.apsn.MarineClinic.Model.Seaman;
import com.apsn.MarineClinic.Repository.CompanyRepository;
import com.apsn.MarineClinic.Repository.SeamanRepository;
import com.apsn.MarineClinic.dto.Input.SeamanInput;
import com.apsn.MarineClinic.dto.response.SeamanResponse;
import com.apsn.MarineClinic.mapper.SeamanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeamanService {
    @Autowired
    private SeamanRepository seamanRepository;
    @Autowired
    private CompanyRepository companyRepository;
    private SeamanMapper mapper;
    public SeamanService(SeamanMapper mapper){
        this.mapper=mapper;
    }
    public List<SeamanResponse> getAll(){
        return mapper.toSeamanResponseList(seamanRepository.findAll()) ;
    }
    public SeamanResponse getById(Long id){
        return mapper.toSeamanResponse(seamanRepository.findById(id).orElseThrow(RuntimeException::new));
    }
    public SeamanResponse save(SeamanInput input){
        Company company=companyRepository.findById(input.company_Id()).orElseThrow(RuntimeException::new);
        Seaman entity=new Seaman();
        entity.setName(input.name());
        entity.setAddress(input.address());
        entity.setCDCNo(input.CDCNo());
        entity.setBirthday(input.birthday());
        entity.setNationality(input.nationality());
        entity.setRank_Name(input.rankName());
        entity.setCompany(company);
        seamanRepository.save(entity);
        return mapper.toSeamanResponse(entity);
    }
    public List<SeamanResponse> findByName(String name){
        return mapper.toSeamanResponseList(seamanRepository.findByName(name)) ;
    }
    public SeamanResponse update(Long id,SeamanInput input){
        Optional<Seaman> optional= seamanRepository.findById(id);
        if(optional.isPresent()) {
            Company company=companyRepository.findById(input.company_Id()).orElseThrow(RuntimeException::new);
            Seaman entity=optional.get();
            entity.setName(input.name());
            entity.setAddress(input.address());
            entity.setCDCNo(input.CDCNo());
            entity.setBirthday(input.birthday());
            entity.setNationality(input.nationality());
            entity.setRank_Name(input.rankName());
            entity.setCompany(company);
            seamanRepository.save(entity);
            return mapper.toSeamanResponse(entity);
        } else throw new RuntimeException("Seaman not found");
    }
    public List<SeamanResponse> getByName(String name){
        return mapper.toSeamanResponseList(seamanRepository.findByName(name)) ;
    }
    public List<SeamanResponse> getByCDCNo(String Cdcno){
        return mapper.toSeamanResponseList(seamanRepository.findByCDCNo(Cdcno)) ;
    }
    public List<SeamanResponse> getByCompany(Long id){
        return mapper.toSeamanResponseList(seamanRepository.findByCompanyId(id)) ;
    }
    public List<SeamanResponse> getByRank(String rank){
        return mapper.toSeamanResponseList(seamanRepository.findByRank(rank));
    }
    public boolean deleteById(Long id){
        seamanRepository.deleteById(id);
        return true;
    }
}
