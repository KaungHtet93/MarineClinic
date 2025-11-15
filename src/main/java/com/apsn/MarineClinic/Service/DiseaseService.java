package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.Disease;
import com.apsn.MarineClinic.Repository.DiseaseRepository;
import com.apsn.MarineClinic.dto.Input.DiseaseInput;
import com.apsn.MarineClinic.dto.response.DiseaseResponse;
import com.apsn.MarineClinic.mapper.DiseaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiseaseService {
    @Autowired
    private DiseaseRepository repository;
    private DiseaseMapper mapper;
    public DiseaseService(DiseaseMapper mapper){
        this.mapper=mapper;
    }

    public List<DiseaseResponse> getAll(){
        return mapper.toDiseaseResponseList(repository.findAll()) ;
    }
    public DiseaseResponse getDiseaseById(Long id){
        return mapper.toDiseaseResponse(repository.findById(id).orElseThrow(()->new RuntimeException("Disease not found with id: " + id)));
    }
    public DiseaseResponse save(DiseaseInput input){
        Disease disease=new Disease();
        disease.setName(input.name());
        return  mapper.toDiseaseResponse(repository.save(disease));
    }
    public List<DiseaseResponse> getByName(String name){
        return mapper.toDiseaseResponseList(repository.findByName(name));
    }
    public DiseaseResponse update(Long id, DiseaseInput input){
        Optional<Disease> optional=repository.findById(id);
        if(optional.isPresent()) {
            Disease disease1=optional.get();
            disease1.setName(input.name());
            return  mapper.toDiseaseResponse(repository.save(disease1));
        } else throw new RuntimeException("Disease not found");
    }
//    @Transactional
    public Boolean deleteById(Long id) {
        repository.deleteById(id);
        return true;
    }
}
