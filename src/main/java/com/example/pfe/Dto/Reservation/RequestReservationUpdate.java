package com.example.pfe.Dto.Reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestReservationUpdate {
    Long id;
    Long equipmentsId;
    Long roomsId;
    String state;
    String benefit_status;

}
