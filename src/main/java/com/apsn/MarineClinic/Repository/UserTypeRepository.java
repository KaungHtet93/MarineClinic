package com.apsn.MarineClinic.Repository;

import com.apsn.MarineClinic.Model.Disease;
import com.apsn.MarineClinic.Model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTypeRepository extends JpaRepository< UserType,Long> {
    @Query("SELECT d FROM UserType d WHERE LOWER(d.userTypeName) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<UserType> findByName(@Param("name") String name);
}
