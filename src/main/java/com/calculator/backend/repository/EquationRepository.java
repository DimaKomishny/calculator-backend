package com.calculator.backend.repository;

import com.calculator.backend.entity.Equation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EquationRepository extends JpaRepository<Equation, UUID> {
    @Query(value =
            "select e from Equation e " +
            "join fetch e.roots r where r.root = :root")
    List<Equation> findByRoot(@Param("root") double root);
}
