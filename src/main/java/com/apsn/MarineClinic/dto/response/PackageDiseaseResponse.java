package com.apsn.MarineClinic.dto.response;

import java.util.List;

public record PackageDiseaseResponse(Integer packageId, String name, List<DiseaseResponse> responseList) {
}
