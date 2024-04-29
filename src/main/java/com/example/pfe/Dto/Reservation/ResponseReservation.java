package com.example.pfe.Dto.Reservation;

import com.example.pfe.Dto.Benefit.ResponseEquipments;
import com.example.pfe.Dto.Benefit.ResponseRooms;
import com.example.pfe.Entities.Equipments;
import com.example.pfe.Entities.Reservation;
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
public class ResponseReservation {

    Long id;
    Long equipmentsId;
    Long roomsId;
    String name;
    String category;
    String subCategory;
    String departDate;
    String departHour;
    String returnHour;
    private Instant createdAt;
    private Instant updatedAt;
    private List<ResponseEquipments> equipments ;
    private List<ResponseRooms> rooms ;

    public static ResponseReservation makeReservation(Reservation reservation){
        return ResponseReservation.builder()
                .id(reservation.getId())
                .name(reservation.getName())
                .category(reservation.getCategory())
                .subCategory(reservation.getSubCategory())
                .departDate(reservation.getDepartDate())
                .departHour(reservation.getDepartHour())
                .returnHour(reservation.getReturnHour())
                .createdAt(reservation.getCreatedAt())
                .updatedAt(reservation.getUpdatedAt())
                .build();
    }

    public static ResponseReservation makeReservationWithEquipmentsAndRooms(Reservation reservation){
        List<Equipments> equipments1 = reservation.getEquipments();
        List<ResponseEquipments> usersFormated1 = new ArrayList<>();
        for (Equipments user : equipments1){
            ResponseEquipments member = ResponseEquipments.makeEquipments(user);
            usersFormated1.add(member);
        }

        List<Rooms> rooms1 = reservation.getRooms();
        List<ResponseRooms> usersFormated2 = new ArrayList<>();
        for (Rooms user : rooms1){
            ResponseRooms member = ResponseRooms.makeRooms(user);
            usersFormated2.add(member);
        }


        return ResponseReservation.builder()
                .id(reservation.getId())
                .name(reservation.getName())
                .departDate(reservation.getDepartDate())
                .departHour(reservation.getDepartHour())
                .returnHour(reservation.getReturnHour())
                .createdAt(reservation.getCreatedAt())
                .updatedAt(reservation.getUpdatedAt())
                .equipments(usersFormated1)
                .rooms(usersFormated2)
                .build();
    }



}
