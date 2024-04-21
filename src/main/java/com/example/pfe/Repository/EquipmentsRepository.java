package com.example.pfe.Repository;

import com.example.pfe.Entities.Equipments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface EquipmentsRepository extends JpaRepository<Equipments,Long> {

    Optional<Equipments> findByType(String type);
}
