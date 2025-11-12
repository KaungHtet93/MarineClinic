package com.apsn.MarineClinic.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;
    @ManyToOne
    @JoinColumn(name="packageId")
    private PackageEntity packageEntity;
    private Date createDate;
    private String Remark;
    @ManyToOne
    @JoinColumn(name="seamanId")
    private Seaman seaman;
    @ManyToOne
    @JoinColumn(name="doctorId")
    private MedicalStaff staff;
}
