package com.example.abstractroutingdatasource01.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeResDto {
    private String name;
    private String branch;
}
