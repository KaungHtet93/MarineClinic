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
@Table(name = "disease")
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long disease_Id;
    private String name;
    @ManyToMany(mappedBy = "diseaseList")
    private List<PackageEntity> packageList;

    @ManyToMany(mappedBy = "diseaseList")
    private List<MedicalStaff> staffList;
}
