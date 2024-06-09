package com.example.pfe.Dto.Benefit;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRooms {
    Long id;
    String name;
    String type;
    String location;
    String maintenance_status;
    Integer capacity;
    String state;
    @Min(value = 1 , message = "benefit is required")
    Long benefitId;
    private String category="Rooms";



}
