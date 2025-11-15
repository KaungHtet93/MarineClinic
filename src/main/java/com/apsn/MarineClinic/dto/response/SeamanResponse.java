package com.apsn.MarineClinic.dto.response;

import java.util.Date;

public record SeamanResponse(Long seaman_Id, String CDCNo, String name, Date birthday, String address, String nationality, String rankName, CompanyNameResponse companyName) {
}
