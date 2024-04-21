package com.example.pfe.Repository;

import com.example.pfe.Entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface RoomsRepository extends JpaRepository<Rooms,Long> {
}
