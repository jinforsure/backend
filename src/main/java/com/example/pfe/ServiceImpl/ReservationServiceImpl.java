package com.example.pfe.ServiceImpl;

import com.example.pfe.Dto.Reservation.RequestReservation;
import com.example.pfe.Dto.Reservation.RequestReservationUpdate;
import com.example.pfe.Dto.Reservation.ResponseReservation;
import com.example.pfe.Entities.Reservation;
import com.example.pfe.Repository.ReservationRepository;
import com.example.pfe.Service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Override
    public List<ResponseReservation> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ResponseReservation> reservationFormated = new ArrayList<>();
        for (Reservation reservation : reservations){
            ResponseReservation member = ResponseReservation.makeReservation(reservation);
            reservationFormated.add(member);
        }
        return reservationFormated;
    }

    @Override
    public Boolean deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            return false;
        }
        reservationRepository.deleteById(id);
        return true;
    }

    @Override
    public void createReservation(RequestReservation requestReservation) {
        Reservation reservation = Reservation.builder()
                .equipmentsId(requestReservation.getEquipmentsId())
                .username(requestReservation.getUsername())
                .name(requestReservation.getName())
                .category(requestReservation.getCategory())
                .subCategory(requestReservation.getSubCategory())
                .roomsId(requestReservation.getRoomsId())
                .equipmentsId(requestReservation.getEquipmentsId())
                .departDate(requestReservation.getDepartDate())
                .departHour(requestReservation.getDepartHour())
                .returnHour(requestReservation.getReturnHour())
                .state(requestReservation.getState())
                .benefit_status(requestReservation.getBenefit_status())
                .build();
        reservationRepository.save(reservation);

    }


    @Override
    public Reservation updateReservation(Long id, RequestReservationUpdate reservationUpdate) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        if (reservationUpdate.getState() != null){
            reservation.setState(reservationUpdate.getState());
        }
        if (reservationUpdate.getBenefit_status() != null){
            reservation.setBenefit_status(reservationUpdate.getBenefit_status());
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public ResponseReservation getReservationById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return ResponseReservation.makeReservation((reservation.get()));
    }



}
