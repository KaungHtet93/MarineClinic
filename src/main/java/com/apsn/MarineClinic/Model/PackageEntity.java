package com.apsn.MarineClinic.Model;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "package")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageId;

    private String name;
    private Double price;

    @OneToMany(mappedBy = "packageEntity")
    private List<Result> results;
    @ManyToMany
    @JoinTable(
            name = "packagedisease",
            joinColumns = @JoinColumn(name = "packageId"),
            inverseJoinColumns = @JoinColumn(name = "diseaseId")
    )
    List<Disease> diseaseList;
}
