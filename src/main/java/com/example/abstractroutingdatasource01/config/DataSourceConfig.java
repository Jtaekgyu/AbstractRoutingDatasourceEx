package com.example.abstractroutingdatasource01.config;

import com.example.abstractroutingdatasource01.entity.Employee;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.abstractroutingdatasource01.repository",
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transcationManager"
)
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        DataSourceRouting dataSourceRouting = new DataSourceRouting();
        dataSourceRouting.setTargetDataSources(targetDataSources());
        dataSourceRouting.setDefaultTargetDataSource(koreaDataSource());
        return dataSourceRouting;
    }

    private Map<Object, Object> targetDataSources() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(BranchEnum.KOREA, koreaDataSource());
        targetDataSources.put(BranchEnum.JAPAN, japanDataSource());
        return targetDataSources;
    }

    @Bean
    @ConfigurationProperties("datasource.korea")
    public DataSourceProperties koreaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource koreaDataSource() {
        return koreaDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @ConfigurationProperties("datasource.japan")
    public DataSourceProperties japanDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource japanDataSource() {
        return japanDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "entityManagerFactory") // 이게 문제였네
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder) { // import할 때 조심하자
        return builder.dataSource(dataSource()).packages(Employee.class)
                .build();
    }

    @Bean(name = "transcationManager")
    public JpaTransactionManager transactionManager(
            @Autowired @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}
