package com.apsn.MarineClinic.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record VoucherResponse (Long voucher_Id, String cashier_Name, Double additionalFee, Double totalAmount, LocalDate dateTime, SeamanNameResponse seamanNameResponse, PackageNameResponse packageNameResponse){
}
