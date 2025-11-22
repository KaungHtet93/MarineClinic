package com.apsn.MarineClinic.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "seaman")
@Getter
@Setter

public class Seaman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long seaman_Id;
    private String CDCNo;
    private String name;
    private Date birthday;
    private String address;
    private String phone;
    private String nationality;
    private String rank_Name;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "seaman",fetch = FetchType.LAZY)
    private List<Voucher> voucher;

    @OneToMany(mappedBy = "seaman",fetch = FetchType.LAZY)
    private List<Result> result;

}
