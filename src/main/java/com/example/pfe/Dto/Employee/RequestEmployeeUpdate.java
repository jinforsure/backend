package com.example.pfe.Dto.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestEmployeeUpdate {
    Long id;
    int phoneNumber;
    String account_type;
    String department;
    String job;
}
