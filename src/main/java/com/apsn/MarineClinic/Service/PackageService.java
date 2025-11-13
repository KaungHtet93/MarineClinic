package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.Disease;
import com.apsn.MarineClinic.Model.PackageEntity;

import com.apsn.MarineClinic.Repository.DiseaseRepository;
import com.apsn.MarineClinic.Repository.PackageRepository;
import com.apsn.MarineClinic.dto.Input.PackageInput;
import com.apsn.MarineClinic.dto.response.PackageResponse;
import com.apsn.MarineClinic.mapper.PackageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    private PackageMapper mapper;
    public PackageService(PackageMapper mapper){
        this.mapper=mapper;
    }
    public List<PackageResponse> getAllPackage(){
        return mapper.toPackageResponseList(packageRepository.findAll());
    }
    public PackageResponse getPackageById(Long id){
        return mapper.toPackageResponse(packageRepository.findById(id).orElseThrow(RuntimeException::new));
    }
    public PackageResponse savePackage(PackageInput input){
        PackageEntity packageEntity=new PackageEntity();
        packageEntity.setName(input.name());
        packageEntity.setPrice(input.price());
        packageEntity.setDiseaseList(diseaseRepository.findAllById(input.disease_Id()));
        return mapper.toPackageResponse(packageRepository.save(packageEntity));
    }
    public List<PackageResponse> findPackageByName(String name){
        return mapper.toPackageResponseList(packageRepository.findByName(name)) ;
    }
    public PackageResponse updatePackage(Long id, PackageInput input){
        Optional<PackageEntity> optional= packageRepository.findById(id);
        if(optional.isPresent()) {
            PackageEntity entity=optional.get();
            entity.setName(input.name());
            entity.setPrice(input.price());
            entity.setDiseaseList(diseaseRepository.findAllById(input.disease_Id()));
            packageRepository.save(entity);
            return  mapper.toPackageResponse(entity);
        } else throw new RuntimeException("Package not found");
    }
    public List<PackageResponse> getPackageByDisease(Long id){
        return mapper.toPackageResponseList(packageRepository.findPackagesByDiseaseId(id));
    }

    public Boolean deleteById(Long id) {
        packageRepository.deleteById(id);
        return true;
    }
}
