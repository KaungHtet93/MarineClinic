package com.apsn.MarineClinic.mapper;
import com.apsn.MarineClinic.Model.Disease;
import com.apsn.MarineClinic.dto.response.DiseaseResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface DiseaseMapper {
//    List<Disease> toDiseaseList(List<DiseaseResponse> response);
    List<DiseaseResponse> toDiseaseResponseList(List<Disease> disease);
//    Disease toDisease(DiseaseResponse response);
    DiseaseResponse toDiseaseResponse(Disease disease);
}
