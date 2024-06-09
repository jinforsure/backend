package com.example.pfe.Controller;

import com.example.pfe.Dto.notif.NotificationRequest;
import com.example.pfe.Dto.notif.NotificationResponse;
import com.example.pfe.Service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/arsii/notif")
@RequiredArgsConstructor
public class NotificationController {
    @Autowired
    private NotificationService notificationServiceA;


    @GetMapping("")
    public ResponseEntity<List<NotificationResponse>> getAllNotification(){
        List<NotificationResponse> employees = notificationServiceA.getAllNotification();
        return ResponseEntity.ok(employees);
    }



    @PostMapping("")
    public ResponseEntity<Object> addNotif(@RequestBody @Valid NotificationRequest request){
        notificationServiceA.createNotif(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message","save success !")
                );
}

}
