package com.apsn.MarineClinic.dto.Input;

import java.util.Date;

public record SeamanInput(String CDCNo, String name, Date birthday,String phone, String address, String nationality, String rankName, Long company_Id) {
}
