package com.example.pfe.Dto.Reservation;

import com.example.pfe.Entities.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReservation {

    Long id;

    String username;
    String name;
    String category;
    String subCategory;
    String departDate;
    String departHour;
    String returnHour;
    Long equipmentsId;
    Long roomsId;
    String state;
    String benefit_status;
    private Instant createdAt;
    private Instant updatedAt;

    public static ResponseReservation makeReservation(Reservation reservation){
        return ResponseReservation.builder()
                .id(reservation.getId())
                .username(reservation.getUsername())
                .name(reservation.getName())
                .category(reservation.getCategory())
                .roomsId(reservation.getRoomsId())
                .equipmentsId(reservation.getEquipmentsId())
                .subCategory(reservation.getSubCategory())
                .departDate(reservation.getDepartDate())
                .departHour(reservation.getDepartHour())
                .returnHour(reservation.getReturnHour())
                .state(reservation.getState())
                .benefit_status((reservation.getBenefit_status()))
                .createdAt(reservation.getCreatedAt())
                .updatedAt(reservation.getUpdatedAt())
                .build();
    }




}
