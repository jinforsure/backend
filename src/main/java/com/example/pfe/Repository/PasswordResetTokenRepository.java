package com.example.pfe.Repository;

import com.example.pfe.Dto.PasswordResetToken;
import com.example.pfe.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByEmployee(Employee employee);
    Optional<PasswordResetToken> findByToken(String token);
}

