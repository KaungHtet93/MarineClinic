package com.apsn.MarineClinic.Repository;

import com.apsn.MarineClinic.Model.Disease;
import com.apsn.MarineClinic.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository< User,Long> {
    @Query("SELECT d FROM User d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<User> findByName(@Param("name") String name);
}
