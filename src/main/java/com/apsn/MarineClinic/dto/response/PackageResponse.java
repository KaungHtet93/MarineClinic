package com.apsn.MarineClinic.dto.response;

import com.apsn.MarineClinic.Model.Disease;

import java.util.List;

public record PackageResponse (Integer package_Id, String name, Double price, List<DiseaseResponse> response){
}
