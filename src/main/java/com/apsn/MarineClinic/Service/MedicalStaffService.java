package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.Disease;
import com.apsn.MarineClinic.Model.MedicalStaff;
import com.apsn.MarineClinic.Model.PackageEntity;
import com.apsn.MarineClinic.Model.Role;
import com.apsn.MarineClinic.Repository.DiseaseRepository;
import com.apsn.MarineClinic.Repository.MedicalStaffRepository;
import com.apsn.MarineClinic.Repository.RoleRepository;
import com.apsn.MarineClinic.dto.Input.MedicalStaffInput;
import com.apsn.MarineClinic.dto.response.MedicalStaffResponse;
import com.apsn.MarineClinic.mapper.MedicalStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalStaffService {
    @Autowired
    private MedicalStaffRepository medicalStaffRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private RoleRepository roleRepository;
    private MedicalStaffMapper mapper;
    public MedicalStaffService(MedicalStaffMapper mapper){
        this.mapper=mapper;
    }
    public List<MedicalStaffResponse> getAllMedicalStaff(){
        return mapper.toMedicalResponseList(medicalStaffRepository.findAll()) ;
    }
    public MedicalStaffResponse getMedicalStaffById(Long id){
        return mapper.toMedicalStaffResponse(medicalStaffRepository.findById(id).orElseThrow(RuntimeException::new));
    }
    public MedicalStaffResponse saveMedicalStaff(MedicalStaffInput input){
        Role role=roleRepository.findById(input.roleId()).orElseThrow(RuntimeException::new);
        MedicalStaff entity=new MedicalStaff();
        entity.setName(input.name());
        entity.setEmail(input.email());
        entity.setPhone(input.phone());
        entity.setRole(role);
        entity.setSpecialization(input.specialization());
        entity.setQualification(input.qualification());
        entity.setDiseaseList(diseaseRepository.findAllById(input.diseaseId()));
        medicalStaffRepository.save(entity);
        return mapper.toMedicalStaffResponse(entity);
    }
    public List<MedicalStaffResponse> findStaffByName(String name){
        return mapper.toMedicalResponseList(medicalStaffRepository.findByName(name)) ;
    }
    public MedicalStaffResponse updateMedicalStaff(Long id,MedicalStaffInput input){
        Optional<MedicalStaff> optional= medicalStaffRepository.findById(id);
        if(optional.isPresent()) {
            Role role=roleRepository.findById(input.roleId()).orElseThrow(RuntimeException::new);
            MedicalStaff entity=new MedicalStaff();
            entity.setName(input.name());
            entity.setEmail(input.email());
            entity.setPhone(input.phone());
            entity.setRole(role);
            entity.setSpecialization(input.specialization());
            entity.setQualification(input.qualification());
            entity.setDiseaseList(diseaseRepository.findAllById(input.diseaseId()));
            return mapper.toMedicalStaffResponse(entity);
        } else throw new RuntimeException("Staff not found");
    }
    public List<MedicalStaffResponse> findStaffByQualification(String name){
        return mapper.toMedicalResponseList(medicalStaffRepository.findByQualification(name));
    }
    public List<MedicalStaffResponse> findDoctor(){
        return mapper.toMedicalResponseList(medicalStaffRepository.findDoctor()) ;
    }
    public List<MedicalStaffResponse> getByName(String name){
        return mapper.toMedicalResponseList(medicalStaffRepository.findByName(name)) ;
    }
    public boolean deleteById(Long id){
        medicalStaffRepository.deleteById(id);
        return true;
    }
}
