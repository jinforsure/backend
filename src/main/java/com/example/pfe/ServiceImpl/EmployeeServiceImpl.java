package com.example.pfe.ServiceImpl;

import com.example.pfe.Dto.Employee.RequestEmployee;
import com.example.pfe.Dto.Employee.RequestEmployeeUpdate;
import com.example.pfe.Dto.Employee.ResponseEmployee;
import com.example.pfe.Dto.LoginDTO;
import com.example.pfe.Dto.LoginMessage;
import com.example.pfe.Entities.Employee;
import com.example.pfe.Repository.EmployeeRepository;
import com.example.pfe.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<ResponseEmployee> getAllEmployee() {
        List< Employee> employees = employeeRepository.findAll();
        List<ResponseEmployee> userFormated = new ArrayList<>();
        for (Employee user : employees){
            ResponseEmployee member = ResponseEmployee.makeEmployee(user);
            userFormated.add(member);
        }
        return userFormated;
    }

    @Override
    public void createEmployee(RequestEmployee employeeRequest) {
        Employee employee = new Employee(
                employeeRequest.getId(),
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getPhoneNumber(),
                employeeRequest.getAddress(),
                employeeRequest.getEmail(),
                this.passwordEncoder.encode(employeeRequest.getPassword()),
                employeeRequest.getAccount_type(),
                employeeRequest.getDepartment(),
                employeeRequest.getJob()

        );

        employeeRepository.save(employee);
    }

    @Override
    public ResponseEmployee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return ResponseEmployee.makeEmployee((employee.get()));
    }

    @Override
    public LoginMessage loginEmployee(LoginDTO loginDTO) {
        String msg ="";
        Employee employee1 = employeeRepository.findByEmail(loginDTO.getEmail() );
        if(employee1 != null) {
            String password = loginDTO.getPassword();
            String storedPassword = employee1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, storedPassword);
            if (isPwdRight) {
                Optional<Employee> employee = employeeRepository.findOneByEmailAndPassword(loginDTO.getEmail(), storedPassword);
                if (employee.isPresent()) {
                    return new LoginMessage("login success", true);
                } else {
                    return new LoginMessage("login failed", false);
                }
            } else {
                return new LoginMessage("password not match", false);
            }
        } else{
                return new LoginMessage("email does not match", false);
            }
    }

    @Override
    public Employee updateEmployee(Long id, RequestEmployeeUpdate employeeRequest) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        if (employeeRequest.getPhoneNumber() != 00000000){
            employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        }
        if (employeeRequest.getAccount_type() != null){
            employee.setAccount_type(employeeRequest.getAccount_type());
        }
        if (employeeRequest.getDepartment() != null){
            employee.setDepartment(employeeRequest.getDepartment());
        }
        if (employeeRequest.getJob() != null){
            employee.setJob(employeeRequest.getJob());
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Boolean deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }
}
