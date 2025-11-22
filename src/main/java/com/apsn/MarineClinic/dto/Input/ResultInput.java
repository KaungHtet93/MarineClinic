package com.apsn.MarineClinic.dto.Input;

import java.time.LocalDate;
public record ResultInput (Long package_Id, LocalDate createDate, String note, Long seaman_Id, Long doctor_Id){
}
