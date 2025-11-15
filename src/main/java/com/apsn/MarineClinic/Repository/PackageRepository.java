package com.apsn.MarineClinic.Repository;

import com.apsn.MarineClinic.Model.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity ,Long> {
    @Query("SELECT d FROM PackageEntity d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<PackageEntity> findByName(@Param("name") String name);
    @Query("SELECT p FROM PackageEntity p JOIN p.diseaseList d WHERE d.disease_Id= :diseaseId")
    List<PackageEntity> findPackagesByDiseaseId(@Param("diseaseId") Long id);
    @Query("SELECT price FROM PackageEntity WHERE package_Id=:packageId")
    Double findPriceById(@Param("packageId") Long id);
}
