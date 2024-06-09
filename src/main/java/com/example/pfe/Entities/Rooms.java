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
@Table(name = "rooms")
@Builder
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String type;
    String location;
    String maintenance_status;
    Integer capacity;
    String state;
    private String category="Rooms";

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
