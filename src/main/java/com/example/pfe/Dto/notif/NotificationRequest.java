package com.example.pfe.Dto.notif;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

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
