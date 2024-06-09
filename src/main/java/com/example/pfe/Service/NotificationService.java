package com.example.pfe.Service;

import com.example.pfe.Dto.notif.NotificationRequest;
import com.example.pfe.Dto.notif.NotificationResponse;

import java.util.List;

public interface NotificationService {


    List<NotificationResponse> getAllNotification();
    void createNotif(NotificationRequest notificationRequest);
}