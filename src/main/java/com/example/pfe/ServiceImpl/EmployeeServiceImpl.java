package com.example.pfe.ServiceImpl;

import com.example.pfe.Dto.Employee.RequestEmployee;
import com.example.pfe.Dto.Employee.RequestEmployeeUpdate;
import com.example.pfe.Dto.Employee.ResponseEmployee;
import com.example.pfe.Dto.LoginDTO;
import com.example.pfe.Dto.LoginMessage;
import com.example.pfe.Dto.PasswordResetToken;
import com.example.pfe.Entities.Employee;
import com.example.pfe.Repository.EmployeeRepository;
import com.example.pfe.Repository.PasswordResetTokenRepository;
import com.example.pfe.Service.EmailService;
import com.example.pfe.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public List<ResponseEmployee> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        List<ResponseEmployee> userFormated = new ArrayList<>();
        for (Employee user : employees) {
            ResponseEmployee member = ResponseEmployee.makeEmployee(user);
            userFormated.add(member);
        }
        return userFormated;
    }

    @Override
    public void createEmployee(RequestEmployee employeeRequest) {
        // Vérifiez si un employé avec cet e-mail existe déjà
        Employee existingEmployee = employeeRepository.findByEmail(employeeRequest.getEmail());
        if (existingEmployee != null) {
            // Gérer le cas où l'employé existe déjà avec cet e-mail
            // Afficher un message d'erreur
            System.out.println("Employee with email " + employeeRequest.getEmail() + " already exists");
            // Vous pouvez également journaliser le message d'erreur dans vos journaux de l'application si nécessaire
            // Log.error("Employee with email " + employeeRequest.getEmail() + " already exists");

            // Arrêtez l'exécution de la méthode
            return;
        }

        // Si l'employé n'existe pas encore, créez-le
        Employee employee = new Employee(
                employeeRequest.getId(),
                employeeRequest.getUsername(),
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getPhoneNumber(),
                employeeRequest.getAddress(),
                employeeRequest.getEmail(),
                this.passwordEncoder.encode(employeeRequest.getPassword()),
                employeeRequest.getAccount_type(),
                employeeRequest.getDepartment(),
                employeeRequest.getJob(),
                employeeRequest.getState()
        );

        employeeRepository.save(employee);
    }


    @Override
    public ResponseEmployee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return ResponseEmployee.makeEmployee((employee.get()));
    }

    @Override
    public Optional<Employee> getEmployeeByEmail(String email) {
        // Utilisez le repository pour rechercher l'employé par e-mail
        return employeeRepository.getByEmail(email);
    }

    @Override
    public LoginMessage loginEmployee(LoginDTO loginDTO) {
        String msg = "";
        Employee employee1 = employeeRepository.findByEmail(loginDTO.getEmail());
        if (employee1 != null) {
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
        } else {
            return new LoginMessage("email does not match", false);
        }
    }

    @Override
    public Employee updateEmployee(Long id, RequestEmployeeUpdate employeeRequest) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        if (employeeRequest.getUsername() != null) {
            employee.setUsername(employeeRequest.getUsername());
        }
        if (employeeRequest.getPhoneNumber() != 00000000) {
            employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        }
        if (employeeRequest.getAccount_type() != null) {
            employee.setAccount_type(employeeRequest.getAccount_type());
        }
        if (employeeRequest.getDepartment() != null) {
            employee.setDepartment(employeeRequest.getDepartment());
        }
        if (employeeRequest.getJob() != null) {
            employee.setJob(employeeRequest.getJob());
        }
        if (employeeRequest.getState() != null) {
            employee.setState(employeeRequest.getState());
        }
        if (employeeRequest.getAddress() != null) {
            employee.setAddress(employeeRequest.getAddress());
        }
        if (employeeRequest.getEmail() != null) {
            employee.setEmail(employeeRequest.getEmail());
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

    // EmployeeServiceImpl.java

    @Override
    public void changePassword(String email, String currentPassword, String newPassword) {
        // Retrieve employee by email
        Optional<Employee> optionalEmployee = employeeRepository.getByEmail(email);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            // Check if the provided current password matches the stored password
            if (passwordEncoder.matches(currentPassword, employee.getPassword())) {
                // Update password with the new password
                employee.setPassword(passwordEncoder.encode(newPassword));
                employeeRepository.save(employee);
            } else {
                throw new RuntimeException("Incorrect current password.");
            }
        } else {
            throw new RuntimeException("Employee not found for email: " + email);
        }
    }

    @Override
    public void resetPassword(String email) {
        Optional<Employee> employeeOpt = employeeRepository.getByEmail(email);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            String token = UUID.randomUUID().toString();
            createPasswordResetTokenForEmployee(employee, token);
            sendPasswordResetEmail(email, token);
        } else {
            System.out.println("Employee not found with email: " + email);
        }
    }

    @Override
    public void createPasswordResetTokenForEmployee(Employee employee, String token) {
        Optional<PasswordResetToken> existingTokenOpt = tokenRepository.findByEmployee(employee);
        PasswordResetToken passwordResetToken;

        if (existingTokenOpt.isPresent()) {
            passwordResetToken = existingTokenOpt.get();
            passwordResetToken.setToken(token);
            passwordResetToken.setExpiryDate(LocalDateTime.now().plusHours(24));
        } else {
            passwordResetToken = new PasswordResetToken();
            passwordResetToken.setEmployee(employee);
            passwordResetToken.setToken(token);
            passwordResetToken.setExpiryDate(LocalDateTime.now().plusHours(24));
        }

        tokenRepository.save(passwordResetToken);
    }

    private void sendPasswordResetEmail(String email, String token) {
        String[] to = {email};
        String[] cc = {}; // No CC in this case
        String subject = "Password Reset Request";
        String url = "http://localhost:4200/reset-password?token=" + token; // Adjust URL as needed
        String body = "To reset your password, click the link below:\n" + url;

        emailService.sendMail(to, cc, subject, body);
    }

    @Override
    public boolean validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isPresent()) {
            PasswordResetToken resetToken = tokenOpt.get();
            return !resetToken.getExpiryDate().isBefore(LocalDateTime.now());
        }
        return false;
    }

    @Override
    public void updatePassword(String token, String newPassword) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isPresent() && validatePasswordResetToken(token)) {
            PasswordResetToken resetToken = tokenOpt.get();
            Employee employee = resetToken.getEmployee();
            employee.setPassword(passwordEncoder.encode(newPassword));
            employeeRepository.save(employee);
            tokenRepository.delete(resetToken);
        } else {
            throw new RuntimeException("Invalid or expired token.");
        }
    }
}