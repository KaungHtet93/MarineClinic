package com.apsn.MarineClinic.mapper;

import com.apsn.MarineClinic.Model.Disease;
import com.apsn.MarineClinic.Model.MedicalStaff;
import com.apsn.MarineClinic.dto.response.DiseaseResponse;
import com.apsn.MarineClinic.dto.response.MedicalStaffResponse;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface MedicalStaffMapper {
    List<MedicalStaff> toMedicalStaffList(List<DiseaseResponse> response);
    List<MedicalStaffResponse> toMedicalResponseList(List<MedicalStaff> staff);
    MedicalStaff toMedicalStaff(MedicalStaffResponse response);
    MedicalStaffResponse toMedicalStaffResponse(MedicalStaff staff);
}
