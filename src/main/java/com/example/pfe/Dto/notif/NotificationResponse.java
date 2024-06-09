package com.example.pfe.Dto.notif;


import com.example.pfe.Entities.Notificationn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
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

    private Instant createdAt;
    private Instant updatedAt;


    public static NotificationResponse makeNotification(Notificationn notification){
        return NotificationResponse.builder()
                .id(notification.getId())
                .date_envoi(notification.getDate_envoi())
                .date_reception(notification.getDate_reception())
                .heure(notification.getHeure())
                .type(notification.getType())
                .message(notification.getMessage())
                .receiver(notification.getReceiver())
                .statut_lecture(notification.getStatut_lecture())
                .id_employee(notification.getId_employee())
                .titre(notification.getTitre())
                .createdAt(notification.getCreatedAt())
                .updatedAt(notification.getUpdatedAt())
                .build();
    }

}
