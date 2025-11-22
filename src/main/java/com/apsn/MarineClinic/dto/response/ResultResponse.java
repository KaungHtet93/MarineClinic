package com.apsn.MarineClinic.dto.response;

import java.time.LocalDate;


public record ResultResponse(Long result_Id, LocalDate created_Date, String note, SeamanResponse seamanNameResponse, StaffNameResponse doctorName, PackageNameResponse packageNameResponse) {
}
