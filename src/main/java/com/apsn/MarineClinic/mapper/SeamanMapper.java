package com.apsn.MarineClinic.mapper;

import com.apsn.MarineClinic.Model.Seaman;
import com.apsn.MarineClinic.dto.response.SeamanResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})

public interface SeamanMapper {
    List<SeamanResponse> toSeamanResponseList(List<Seaman> seamanList);
    @Mapping(source = "seaman_Id", target = "seaman_Id")
    @Mapping(source="rank_Name", target="rankName")
    @Mapping(source="company", target="companyName")
    SeamanResponse toSeamanResponse(Seaman seaman);
}
