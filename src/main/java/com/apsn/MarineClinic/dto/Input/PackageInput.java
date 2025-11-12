package com.apsn.MarineClinic.dto.Input;

import java.util.List;

public record PackageInput( String name,Double price, List<Long> diseaseId) {
}
