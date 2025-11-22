package com.apsn.MarineClinic.dto.response;

import java.util.Date;

public record SeamanResponse(Long seaman_Id, String CDCNo, String name,String phone, Date birthday, String address, String nationality, String rankName, CompanyNameResponse companyName) {
}
