package com.apsn.MarineClinic.dto.response;

import java.util.List;

public record PackageDiseaseResponse(Integer package_Id, String name, List<DiseaseResponse> responseList) {
}
