package com.apsn.MarineClinic.Repository;

import com.apsn.MarineClinic.Model.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalStaffRepository extends JpaRepository<MedicalStaff,Long> {
    @Query("SELECT d FROM MedicalStaff d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<MedicalStaff> findByName(@Param("name") String name);
    @Query("SELECT d FROM MedicalStaff d WHERE LOWER(d.qualification) LIKE LOWER(CONCAT('%',:qualification,'%'))")
    List<MedicalStaff> findByQualification(@Param("qualification") String name);
    @Query("SELECT d FROM MedicalStaff d join d.role v where v.name Like Lower('doctor')")
    List<MedicalStaff> findDoctor();
    @Query("""
       SELECT ms
       FROM MedicalStaff ms
       LEFT JOIN FETCH ms.diseaseList d
       """)
    List<MedicalStaff> getStaffWithDiseases();
}
