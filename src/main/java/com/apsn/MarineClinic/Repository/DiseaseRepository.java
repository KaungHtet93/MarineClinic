package com.apsn.MarineClinic.Repository;

import com.apsn.MarineClinic.Model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease,Long > {
    @Query("SELECT d FROM Disease d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Disease> findByName(@Param("name") String name);
}
