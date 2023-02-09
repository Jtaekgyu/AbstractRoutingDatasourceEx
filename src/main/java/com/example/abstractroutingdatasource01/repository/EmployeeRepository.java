package com.example.abstractroutingdatasource01.repository;

import com.example.abstractroutingdatasource01.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByName(String name);
}
