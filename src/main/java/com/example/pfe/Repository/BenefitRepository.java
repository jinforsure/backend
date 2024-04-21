package com.example.pfe.Repository;

import com.example.pfe.Entities.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit,Long> {
}
