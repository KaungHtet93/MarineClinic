package com.apsn.MarineClinic.mapper;

import com.apsn.MarineClinic.Model.MedicalStaff;
import com.apsn.MarineClinic.dto.response.MedicalStaffResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface MedicalStaffMapper {
    List<MedicalStaffResponse> toMedicalResponseList(List<MedicalStaff> staff);
    @Mapping(source = "medicalStaff_Id", target = "medicalStaff_Id")
    @Mapping(source = "diseaseList", target = "response")
    MedicalStaffResponse toMedicalStaffResponse(MedicalStaff staff);
}
