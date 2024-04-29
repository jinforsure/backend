package com.example.pfe.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long equipmentsId;
    Long roomsId;
    String name;
    String category;
    String subCategory;
    String departDate;
    String departHour;
    String returnHour;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(mappedBy = "reservation")
    private List<Equipments> equipments ;
    @OneToMany(mappedBy = "reservation")
    private List<Rooms> rooms ;
}
