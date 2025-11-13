package com.apsn.MarineClinic.Repository;

import com.apsn.MarineClinic.Model.MedicalStaff;
import com.apsn.MarineClinic.Model.Seaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeamanRepository extends JpaRepository<Seaman, Long> {
    @Query("SELECT d FROM Seaman d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Seaman> findByName(@Param("name") String name);
    @Query("SELECT d FROM Seaman d WHERE LOWER(d.CDCNo) LIKE LOWER(CONCAT('%',:cdcno,'%'))")
    List<Seaman> findByCDCNo(@Param("cdcno") String cdcno);
    @Query("SELECT o FROM Seaman o WHERE o.visitedDate BETWEEN :startDate AND :endDate ORDER BY o.visitedDate ASC")
    List<Seaman> findByLastVisitedDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate")LocalDate endDate
    );
    @Query("SELECT d FROM Seaman d WHERE LOWER(d.rankName) LIKE LOWER(CONCAT('%',:rankName,'%'))")
    List<Seaman> findByRank(@Param("rankName") String rank);
    @Query("SELECT d From Seaman d join d.company s where s.company_Id=:companyId")
    List<Seaman> findByCompanyId(@Param("companyId")Long id);
}
