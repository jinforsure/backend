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
@Table(name = "notificationn")
@Builder
public class Notificationn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    Long id;
    String date_envoi;
    String date_reception;
    String heure;
    String type;
    String titre;
    String message;
    String receiver;
    String statut_lecture;
    Long id_employee;



    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;













}
