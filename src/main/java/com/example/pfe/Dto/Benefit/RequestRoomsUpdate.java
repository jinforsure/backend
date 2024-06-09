package com.example.pfe.Dto.Benefit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRoomsUpdate {
    Long id;
    String maintenance_status;
    Integer capacity;
    String state;
    Long benefitId;


}
