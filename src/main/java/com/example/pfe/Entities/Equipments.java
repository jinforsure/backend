package com.example.pfe.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipments")
@Builder
public class Equipments {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String type;  //type d'equipment info , securite ..
    String manufactuer; // societe eli san3t el equip
    String model;
    Integer quantity;
    Integer price;
    String maintenance_status;
    String state ;
    private String category="Equipments";

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "benefit_id")
    private Benefit benefit;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;


}
