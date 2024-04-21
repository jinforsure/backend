package com.example.pfe.Dto.Benefit;

import com.example.pfe.Entities.Benefit;
import com.example.pfe.Entities.Equipments;
import com.example.pfe.Entities.Rooms;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBenefit {

    Long id;
    String category;
    private Instant createdAt;
    private Instant updatedAt;
    private List<ResponseEquipments> equipmentsList;
    private List<ResponseRooms> roomsList ;

    public static ResponseBenefit makeBenefit(Benefit benefit){
        return ResponseBenefit.builder()
                .id(benefit.getId())
                .category(benefit.getCategory())
                .createdAt(benefit.getCreatedAt())
                .updatedAt(benefit.getUpdatedAt())
                .build();
    }
    public static ResponseBenefit makeBenefitList(Benefit benefit){
        List<Equipments> equipments = benefit.getEquipments();
        List<Rooms> rooms = benefit.getRooms();
        List<ResponseEquipments> equipmentsFormated = new ArrayList<>();
        List<ResponseRooms> roomsFormated = new ArrayList<>();
        for (Equipments equipments1 : equipments){
            ResponseEquipments member = ResponseEquipments.makeEquipments(equipments1);
            equipmentsFormated.add(member);
        }
        for (Rooms rooms1 : rooms){
            ResponseRooms member = ResponseRooms.makeRooms(rooms1);
            roomsFormated.add(member);
        }
        return ResponseBenefit.builder()
                .id(benefit.getId())
                .category(benefit.getCategory())
                .createdAt(benefit.getCreatedAt())
                .updatedAt(benefit.getUpdatedAt())
                .equipmentsList(equipmentsFormated)
                .roomsList(roomsFormated)
                .build();
    }
}
