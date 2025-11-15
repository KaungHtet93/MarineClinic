package com.apsn.MarineClinic.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medicalstaff")
public class MedicalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long medicalStaff_Id ;
    private String name;
    private String email;
    private String phone;
    private String specialization;
    private String qualification;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_Id")
    private Role role;
    @ManyToMany
    @JoinTable(
            name = "medicalstaff_disease",
            joinColumns = @JoinColumn(name = "medicalstaff_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    List<Disease> diseaseList;
}
