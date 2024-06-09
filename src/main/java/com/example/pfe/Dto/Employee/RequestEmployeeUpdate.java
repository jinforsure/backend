package com.example.pfe.Dto.Employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    String username;
    String address;
    @NotBlank(message = "email is required")
    @Email(message = "Email not valid")
    String email;
    int phoneNumber;
    String account_type;
    String department;
    String job;
    String state="Active";
}
