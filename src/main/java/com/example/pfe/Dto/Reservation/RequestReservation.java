package com.example.pfe.Dto.Reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestReservation {
    Long id;

    String username;
    Long equipmentsId;
    Long roomsId;
    String name;
    String category;
    String subCategory;
    String departDate;
    String departHour;
    String returnHour;
    String state;
    String benefit_status;

}
