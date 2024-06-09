package com.example.pfe.Dto.Benefit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestEquipmentsUpdate {
    Long id;
    Integer quantity;
    Integer price;
    String maintenance_status;
    String state;
    Long benefitId;


}
