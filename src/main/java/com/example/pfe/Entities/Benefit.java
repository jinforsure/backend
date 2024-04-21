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
@Table(name = "benefit")
@Builder
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String category;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    @OneToMany(mappedBy = "benefit")
    private List<Equipments> equipments ;
    @OneToMany(mappedBy = "benefit")
    private List<Rooms> rooms ;
}
