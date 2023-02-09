package com.example.abstractroutingdatasource01.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceRouting extends AbstractRoutingDataSource {
    // 스프링 프레임워크에서 제공해주는 AbstractRoutingDataSource 에서
    // determineCurrentLookupKey()을 오버라이드하여
    // 어떤 ContextHolder를 통해서 값을 매칭시켜 찾을 지 구현한다.

    // DataSourceRouting 클래스는 AbstractRoutingDatasource 를 상속받은 클래스다.
    // AbstractRoutingDatasource 클래스의 determineCurrentLookupKey 메소드를 오버라이딩 했는데,
    // 위에서 지정한 ContextHolder에서 현재 Context에서 사용될 타겟 DataSource를 꺼내온다.
    @Override
    protected Object determineCurrentLookupKey() {
        return BranchContextHolder.getBranchContext();
    }
}
