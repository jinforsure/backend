package com.example.pfe.ServiceImpl;


import com.example.pfe.Dto.notif.NotificationRequest;
import com.example.pfe.Dto.notif.NotificationResponse;
import com.example.pfe.Entities.Notificationn;
import com.example.pfe.Repository.NotificationRepository;
import com.example.pfe.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService  {


    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public List<NotificationResponse> getAllNotification() {
        List<Notificationn> benefits = notificationRepository.findAll();
        List<NotificationResponse> benefitFormated = new ArrayList<>();
        for (Notificationn notification: benefits) {
            NotificationResponse benefit1 = NotificationResponse.makeNotification(notification);
            benefitFormated.add(benefit1);
        }
        return benefitFormated;
    }

    @Override
    public void createNotif(NotificationRequest notificationRequest) {


        Notificationn notificationn = Notificationn.builder()

                .type(notificationRequest.getType())
                .heure(notificationRequest.getHeure())
                .titre(notificationRequest.getTitre())
                .date_reception(notificationRequest.getDate_reception())
                .message(notificationRequest.getMessage())
                .date_envoi(notificationRequest.getDate_envoi())
                .statut_lecture(notificationRequest.getStatut_lecture())
                .id_employee(notificationRequest.getId_employee())
                .receiver(notificationRequest.getReceiver())

                .build();
        notificationRepository.save(notificationn);
}









}
