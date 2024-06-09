package com.example.pfe.Controller;

import com.example.pfe.Dto.Employee.RequestEmployee;
import com.example.pfe.Dto.Employee.RequestEmployeeUpdate;
import com.example.pfe.Dto.Employee.ResponseEmployee;
import com.example.pfe.Dto.LoginDTO;
import com.example.pfe.Dto.LoginMessage;
import com.example.pfe.Dto.PasswordResetConfirmDTO;
import com.example.pfe.Dto.PasswordResetDTO;
import com.example.pfe.Entities.Employee;
import com.example.pfe.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/arsii/employee")
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<List<ResponseEmployee>> getAllEmployee(){
        List<ResponseEmployee> employees = employeeService.getAllEmployee();
        return ResponseEntity.ok(employees);
    }
    @PostMapping("")
    public ResponseEntity<Object> addEmployee(@RequestBody @Valid RequestEmployee request){
        employeeService.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message","save success !")
        );
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseEmployee> getEmployeeById(@PathVariable Long id){
        return  ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
    @PutMapping(value ="{id}")
    public ResponseEntity<Object> updateEmployee(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid RequestEmployeeUpdate request){
        employeeService.updateEmployee(id,request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                Collections.singletonMap("message","updated success !")
        );
    }
    @DeleteMapping(value ="{id}")
    public ResponseEntity<Object> deleteEmployee (@PathVariable Long id){
        boolean deleteEmployee = employeeService.deleteEmployee(id);
        if (deleteEmployee){
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Collections.singletonMap("message","deleted success !"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap("message","id does not exsist"));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO){
        LoginMessage loginMessage = employeeService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getEmployeeByEmail(@PathVariable String email) {
        Optional<Employee> employee = employeeService.getEmployeeByEmail(email);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found for email: " + email, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<Object> changePassword(@RequestParam String email,
                                                 @RequestParam String currentPassword,
                                                 @RequestParam String newPassword) {
        try {
            // Call service method to change password
            employeeService.changePassword(email, currentPassword, newPassword);
            return ResponseEntity.ok("Password changed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to change password: " + e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestBody PasswordResetDTO passwordResetDTO) {
        employeeService.resetPassword(passwordResetDTO.getEmail());
        return ResponseEntity.ok("Password reset link sent to your email.");
    }

    @PostMapping("/reset-password/confirm")
    public ResponseEntity<Object> confirmResetPassword(@RequestBody PasswordResetConfirmDTO request) {
        if (employeeService.validatePasswordResetToken(request.getToken())) {
            employeeService.updatePassword(request.getToken(), request.getNewPassword());
            return ResponseEntity.ok("Password has been reset successfully.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
    }


}
