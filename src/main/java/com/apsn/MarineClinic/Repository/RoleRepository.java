package com.apsn.MarineClinic.Repository;

import com.apsn.MarineClinic.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository< Role,Long > {
    @Query("SELECT d FROM Role d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Role> findByName(@Param("name") String name);
}
