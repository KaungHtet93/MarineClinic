package com.apsn.MarineClinic.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long voucher_Id;
    @ManyToOne
    @JoinColumn(name = "package_id")
    private PackageEntity aPackage;
    private String cashier_Name;

    private Double additionalFee;
    private Double totalAmount;
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "seaman_id")
    private Seaman seaman;
}
