package com.example.abstractroutingdatasource01.service;

import com.example.abstractroutingdatasource01.controller.dto.request.EmployeeReqDto;
import com.example.abstractroutingdatasource01.controller.dto.response.EmployeeResDto;
import com.example.abstractroutingdatasource01.entity.Employee;
import com.example.abstractroutingdatasource01.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

//    @Autowired
//    private EmployeeRepository employeeRepository;
    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(EmployeeReqDto employeeReqDto){
        employeeRepository.findByName(employeeReqDto.getName()).ifPresent(
                it -> {throw new IllegalArgumentException();}
        );
        Employee employee = Employee.builder()
                        .name(employeeReqDto.getName())
                        .branch(employeeReqDto.getBranch())
                        .build();

        return employeeRepository.save(employee);
    }

    public List<EmployeeResDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResDto> employeeResDtos = new ArrayList<>();
        for(Employee employee : employees){
            employeeResDtos.add(EmployeeResDto.builder()
                    .name(employee.getName())
                    .branch(employee.getBranch())
                    .build()
            );
        }
        return employeeResDtos;
    }
}
