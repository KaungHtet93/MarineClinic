package com.apsn.MarineClinic.dto.Input;

import java.util.List;

public record MedicalStaffInput(String name, String email, String phone, String specialization,Long roleId, String qualification,
                                List<Long> diseaseId){
}
