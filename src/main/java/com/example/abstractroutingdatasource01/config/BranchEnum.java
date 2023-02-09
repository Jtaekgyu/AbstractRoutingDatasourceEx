package com.example.abstractroutingdatasource01.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BranchEnum {
    KOREA("korea"), JAPAN("japan");
    private final String name;

}
