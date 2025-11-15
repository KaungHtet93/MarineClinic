package com.apsn.MarineClinic.mapper;

import com.apsn.MarineClinic.Model.Company;
import com.apsn.MarineClinic.dto.response.CompanyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface CompanyMapper {
    List<CompanyResponse> toCompanyResponseList(List<Company> companies);
    @Mapping(source = "company_Id", target = "company_Id")
    CompanyResponse toCompanyResponse(Company company);

}
