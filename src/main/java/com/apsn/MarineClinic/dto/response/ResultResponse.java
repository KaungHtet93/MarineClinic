package com.apsn.MarineClinic.dto.response;

import java.util.Date;

public record ResultResponse(Long result_Id, Date created_Date, String note, SeamanNameResponse seamanNameResponse, StaffNameResponse doctorName, PackageNameResponse packageNameResponse) {
}
