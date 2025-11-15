package com.apsn.MarineClinic.mapper;

import com.apsn.MarineClinic.Model.PackageEntity;
import com.apsn.MarineClinic.dto.response.PackageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = DiseaseMapper.class)
public interface PackageMapper {
    List<PackageResponse> toPackageResponseList(List<PackageEntity> packageEntity);
    @Mapping(source = "diseaseList", target = "response")
    PackageResponse toPackageResponse(PackageEntity entity);
}
