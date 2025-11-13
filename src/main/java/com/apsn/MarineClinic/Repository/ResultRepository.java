package com.apsn.MarineClinic.Repository;

import com.apsn.MarineClinic.Model.Result;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long> {
}
