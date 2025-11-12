package com.apsn.MarineClinic.mapper;

import com.apsn.MarineClinic.Model.MedicalStaff;
import com.apsn.MarineClinic.Model.PackageEntity;
import com.apsn.MarineClinic.dto.response.DiseaseResponse;
import com.apsn.MarineClinic.dto.response.MedicalStaffResponse;
import com.apsn.MarineClinic.dto.response.PackageResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackageMapper {
//    List<PackageEntity> toPackageEntityList(List<PackageResponse> response);
    List<PackageResponse> toPackageResponseList(List<PackageEntity> packageEntity);
//    PackageEntity toPackage(PackageResponse response);
    PackageResponse toPackageResponse(PackageEntity entity);
}
