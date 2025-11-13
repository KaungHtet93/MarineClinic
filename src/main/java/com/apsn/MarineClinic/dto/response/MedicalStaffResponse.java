package com.apsn.MarineClinic.dto.response;

import com.apsn.MarineClinic.Model.Role;

import java.util.List;

public record MedicalStaffResponse (Long medicalStaff_id, String name, String email, String phone, String specialization, String qualification,
                                    Role role, List<DiseaseResponse> response){
}
