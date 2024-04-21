package com.example.pfe.Dto.Benefit;

import com.example.pfe.Entities.Equipments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEquipments {
    Long id;
    String name;
    String type;  //type d'equipment info , securite ..
    String manufactuer; // societe eli san3t el equip
    String model;
    Date purchase_date; //date d'achat
    Integer quantity;
    Integer price;
    String maintenance_status;
    Long benefitId;
    private Instant createdAt;
    private Instant updatedAt;
    private ResponseBenefit benefit;

    public static ResponseEquipments makeEquipments(Equipments equipments){
        return ResponseEquipments.builder()
                .id(equipments.getId())
                .name(equipments.getName())
                .type(equipments.getType())
                .manufactuer(equipments.getManufactuer())
                .model(equipments.getModel())
                .purchase_date(equipments.getPurchase_date())
                .quantity(equipments.getQuantity())
                .price(equipments.getPrice())
                .maintenance_status(equipments.getMaintenance_status())
                .benefit(ResponseBenefit.makeBenefit(equipments.getBenefit()))
                .createdAt(equipments.getCreatedAt())
                .updatedAt(equipments.getUpdatedAt())
                .build();
    }
}
