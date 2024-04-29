package com.example.pfe.Controller;

import com.example.pfe.Dto.Reservation.RequestReservationUpdate;
import com.example.pfe.Dto.Reservation.RequestReservation;
import com.example.pfe.Dto.Reservation.ResponseReservation;
import com.example.pfe.Service.ReservationService;
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
@RequestMapping("api/arsii/reservation")
@RequiredArgsConstructor
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("")
    public ResponseEntity<List<ResponseReservation>> getAllReservations(){
        List<ResponseReservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("")
    public ResponseEntity<Object> addReservation(@RequestBody @Valid RequestReservation request){
        reservationService.createReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message","save success !")
        );
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseReservation> getReservationById(@PathVariable Long id){
        return  ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PutMapping(value ="{id}")
    public ResponseEntity<Object> updateReservation(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid RequestReservationUpdate request){
        reservationService.updateReservation(id,request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                Collections.singletonMap("message","updated success !")
        );
    }
    @DeleteMapping(value ="{id}")
    public ResponseEntity<Object> deleteReservation (@PathVariable Long id){
        boolean deleteReservation = reservationService.deleteReservation(id);
        if (deleteReservation){
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Collections.singletonMap("message","deleted success !"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap("message","id does not exsist"));
    }
    

}
