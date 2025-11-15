package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.MedicalStaff;
import com.apsn.MarineClinic.Model.PackageEntity;
import com.apsn.MarineClinic.Model.Result;
import com.apsn.MarineClinic.Model.Seaman;
import com.apsn.MarineClinic.Repository.MedicalStaffRepository;
import com.apsn.MarineClinic.Repository.PackageRepository;
import com.apsn.MarineClinic.Repository.ResultRepository;
import com.apsn.MarineClinic.Repository.SeamanRepository;
import com.apsn.MarineClinic.dto.Input.ResultInput;
import com.apsn.MarineClinic.dto.response.ResultResponse;
import com.apsn.MarineClinic.mapper.ResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private SeamanRepository seamanRepository;
    @Autowired
    private MedicalStaffRepository medicalStaffRepository;


    private ResultMapper mapper;
    public ResultService(ResultMapper mapper){
        this.mapper=mapper;
    }
    //get all
    public List<ResultResponse> getAll() {
        return mapper.toResultResponseList(resultRepository.findAll());
    }

    //get by seaman name
    public List<ResultResponse> getBySeamanName(String name) {
        return mapper.toResultResponseList(resultRepository.getResultBySeamanName(name));
    }

    public List<ResultResponse> getByDateBetween(LocalDate startDate, LocalDate endDate) {
        return mapper.toResultResponseList(resultRepository.getResultBetween(startDate, endDate));
    }

    public ResultResponse getById(Long id) {
        return mapper.toResultResponse(resultRepository.findById(id).orElseThrow(()->new RuntimeException("Result not found with id: " + id)));
    }

    //create
    public ResultResponse create(ResultInput input) {
        PackageEntity packageEntity = packageRepository.findById(input.package_Id()).orElseThrow(()->new RuntimeException("Package not found with id: " + input.package_Id()));
        Seaman seaman = seamanRepository.findById(input.seaman_Id()).orElseThrow(()->new RuntimeException("Seaman not found with id: " + input.seaman_Id()));
        MedicalStaff staff = medicalStaffRepository.findById(input.doctor_Id()).orElseThrow(()->new RuntimeException("Doctor not found with id: " + input.doctor_Id()));
        Result result = new Result();
        result.setNote(input.notes());
        result.setCreatedDate(input.createDate());
        result.setStaff(staff);
        result.setSeaman(seaman);
        result.setPackageEntity(packageEntity);
        resultRepository.save(result);
        return mapper.toResultResponse(result);
    }

    //update
    public ResultResponse update(Long id, ResultInput input) {
        Optional<Result> optional = resultRepository.findById(id);
        if (optional.isPresent()) {
            PackageEntity packageEntity = packageRepository.findById(input.package_Id()).orElseThrow(()->new RuntimeException("Package not found with id: " + input.package_Id()));
            Seaman seaman = seamanRepository.findById(input.seaman_Id()).orElseThrow(()->new RuntimeException("Seaman not found with id: " + input.seaman_Id()));
            MedicalStaff staff = medicalStaffRepository.findById(input.doctor_Id()).orElseThrow(()->new RuntimeException("Doctor not found with id: " + input.doctor_Id()));
            Result result = optional.get();
            result.setNote(input.notes());
            result.setCreatedDate(input.createDate());
            result.setStaff(staff);
            result.setSeaman(seaman);
            result.setPackageEntity(packageEntity);
            resultRepository.save(result);
            return mapper.toResultResponse(result);
        } else throw new RuntimeException("Result not found");
    }

    //delete
    public boolean deleteById(Long id) {
        resultRepository.deleteById(id);
        return true;
    }
}
