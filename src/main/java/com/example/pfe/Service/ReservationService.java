package com.example.pfe.Service;

import com.example.pfe.Dto.Reservation.RequestReservationUpdate;
import com.example.pfe.Dto.Reservation.RequestReservation;
import com.example.pfe.Dto.Reservation.ResponseReservation;
import com.example.pfe.Entities.Reservation;

import java.util.List;

public interface ReservationService {
    List<ResponseReservation> getAllReservations();
    Boolean deleteReservation(Long id);
    void createReservation(RequestReservation requestReservation);
    Reservation updateReservation(Long id, RequestReservationUpdate reservationUpdate);
    ResponseReservation getReservationById(Long id);


}
