package com.apsn.MarineClinic.Repository;

import com.apsn.MarineClinic.Model.Result;
import com.apsn.MarineClinic.Model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long> {
    @Query("SELECT d FROM Voucher d join d.seaman v WHERE LOWER(v.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Voucher> getBySeamanName(@Param("name")String name);
    @Query("SELECT d FROM Voucher d  WHERE LOWER(d.cashier_Name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Voucher> getByStaffName(@Param("name")String name);
    @Query("SELECT o FROM Voucher o WHERE o.dateTime BETWEEN :startDate AND :endDate ORDER BY o.dateTime ASC")
    List<Voucher> getVoucherBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate")LocalDate endDate
    );
}
