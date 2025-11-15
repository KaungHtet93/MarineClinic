package com.apsn.MarineClinic.Repository;

import com.apsn.MarineClinic.Model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long> {
    @Query("SELECT d FROM Result d join d.seaman v WHERE LOWER(v.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Result> getResultBySeamanName(@Param("name")String name);
    @Query("SELECT o FROM Result o WHERE o.createdDate BETWEEN :startDate AND :endDate ORDER BY o.createdDate ASC")
    List<Result> getResultBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate")LocalDate endDate
    );
}
