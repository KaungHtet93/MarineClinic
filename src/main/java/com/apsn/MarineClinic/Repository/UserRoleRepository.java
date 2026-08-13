package com.apsn.MarineClinic.Repository;

import com.apsn.MarineClinic.Model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    Optional<UserRole> findByName(String name);

}
