package com.example.pfe.Controller;

import com.example.pfe.Dto.Employee.RequestEmployee;
import com.example.pfe.Dto.Employee.RequestEmployeeUpdate;
import com.example.pfe.Dto.Employee.ResponseEmployee;
import com.example.pfe.Dto.LoginDTO;
import com.example.pfe.Dto.LoginMessage;
import com.example.pfe.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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

}
