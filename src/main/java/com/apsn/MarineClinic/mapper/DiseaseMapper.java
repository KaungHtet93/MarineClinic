package com.apsn.MarineClinic.mapper;

import com.apsn.MarineClinic.Model.Disease;
import com.apsn.MarineClinic.dto.response.DiseaseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface DiseaseMapper {
    //    List<Disease> toDiseaseList(List<DiseaseResponse> response);
    List<DiseaseResponse> toDiseaseResponseList(List<Disease> disease);

    //    Disease toDisease(DiseaseResponse response);
    @Mapping(source = "disease_Id", target = "disease_Id")
    DiseaseResponse toDiseaseResponse(Disease disease);
}
