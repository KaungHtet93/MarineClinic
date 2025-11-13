package com.apsn.MarineClinic.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "seamanprofile")
@Getter
@Setter

public class Seaman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seamanId;
    private String CDCNo;
    private String name;
    private Date birthday;
    private String address;
    private String nationality;
    private String rankName;
    private Date visitedDate;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "seaman",fetch = FetchType.LAZY)
    private List<Voucher> voucher;

    @OneToMany(mappedBy = "seaman",fetch = FetchType.LAZY)
    private List<Result> result;

}
