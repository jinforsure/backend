package com.example.pfe.Repository;

import com.example.pfe.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

   // Optional<Employee> findOneByEmailAndPassword(String email, String password);

    //Employee findByEmail(String email);

    //Optional<Employee> findByEmail(String email);

    //boolean existsByEmail(String email);
}
