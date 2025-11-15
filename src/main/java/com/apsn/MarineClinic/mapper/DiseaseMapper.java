package com.apsn.MarineClinic.mapper;

import com.apsn.MarineClinic.Model.Disease;
import com.apsn.MarineClinic.dto.response.DiseaseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface DiseaseMapper {
    List<DiseaseResponse> toDiseaseResponseList(List<Disease> disease);
    @Mapping(source = "disease_Id", target = "disease_Id")
    @Mapping(source = "name", target = "name")
    DiseaseResponse toDiseaseResponse(Disease disease);
}
