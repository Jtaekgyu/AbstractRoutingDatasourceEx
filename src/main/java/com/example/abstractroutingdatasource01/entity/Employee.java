package com.example.abstractroutingdatasource01.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="employee")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String branch;
//    private Integer age;

    @Builder
    public Employee(String name, String branch){
        this.name = name;
        this.branch = branch;
    }
}
