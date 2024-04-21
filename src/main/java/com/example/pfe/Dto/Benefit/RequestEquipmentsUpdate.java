package com.example.pfe.Dto.Benefit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestEquipmentsUpdate {
    Long id;
    Date purchase_date; //date d'achat
    Integer quantity;
    Integer price;
    String maintenance_status;
    Long benefitId;
}
