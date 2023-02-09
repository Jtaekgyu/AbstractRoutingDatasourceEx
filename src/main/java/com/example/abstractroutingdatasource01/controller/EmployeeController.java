package com.example.abstractroutingdatasource01.controller;

import com.example.abstractroutingdatasource01.controller.dto.request.EmployeeReqDto;
import com.example.abstractroutingdatasource01.controller.dto.response.EmployeeResDto;
import com.example.abstractroutingdatasource01.entity.Employee;
import com.example.abstractroutingdatasource01.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

//    @Autowired
//    private EmployeeService employeeService;
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeeReqDto employeeReqDto) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeReqDto));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResDto>> getEmployees() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(employeeService.getEmployees());
    }
}
