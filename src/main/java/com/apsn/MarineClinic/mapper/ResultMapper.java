package com.apsn.MarineClinic.mapper;

import com.apsn.MarineClinic.Model.Result;
import com.apsn.MarineClinic.dto.response.ResultResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SeamanMapper.class, PackageMapper.class, MedicalStaffMapper.class
})

public interface ResultMapper {
    List<ResultResponse> toResultResponseList(List<Result> results);
    @Mapping(source = "result_Id", target = "result_Id")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "createdDate", target = "created_Date")
    @Mapping(source = "seaman", target = "seamanNameResponse")
    @Mapping(source = "packageEntity", target = "packageNameResponse")
    @Mapping(source = "staff",target="doctorName")
    ResultResponse toResultResponse(Result result);
}

