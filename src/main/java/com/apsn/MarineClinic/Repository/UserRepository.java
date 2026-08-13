package com.apsn.MarineClinic.Repository;

import com.apsn.MarineClinic.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository< User,Long> {
    @Query("SELECT d FROM User d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<User> findByName(@Param("name") String name);
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.email = :email")
    Optional<User> findByEmailWithRole(@Param("email") String email);
    boolean existsByEmailAndUserIdNot(String email, Long id);
}
