package com.apsn.MarineClinic.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long result_Id;
    @ManyToOne
    @JoinColumn(name="package_Id")
    private PackageEntity packageEntity;
    private LocalDate createdDate;
    private String note;
    @ManyToOne
    @JoinColumn(name="seaman_Id")
    private Seaman seaman;
    @ManyToOne
    @JoinColumn(name="doctor_Id")
    private MedicalStaff staff;
}
