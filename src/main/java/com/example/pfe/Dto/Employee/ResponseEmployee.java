package com.example.pfe.Dto.Employee;

import com.example.pfe.Entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEmployee {
    Long id;
    String username;
    String firstName;
    String lastName;
    int phoneNumber;
    String address;
    String email;
    String password;
    String account_type;
    String department;
    String job;
    String state="Active";
    private Instant createdAt;
    private Instant updatedAt;

    public static ResponseEmployee makeEmployee(Employee employee){
        return ResponseEmployee.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .address(employee.getAddress())
                .email(employee.getEmail())
                .password(employee.getPassword())
                .account_type(employee.getAccount_type())
                .department(employee.getDepartment())
                .job(employee.getJob())
                .state(employee.getState())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }

}
