package com.example.multidatabase.config.database;

import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.multidatabase.sms.repository",
    entityManagerFactoryRef = "smsEntityManagerFactory",
    transactionManagerRef = "smsTransactionManager")
public class SmsDataSourceConfig {

    @Value("${spring.datasource.db2.url}")
    private String url;

    @Value("${spring.datasource.db2.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.db2.username}")
    private String username;

    @Value("${spring.datasource.db2.password}")
    private String password;


    @Bean(name = "smsDataSource")
    @ConfigurationProperties("spring.datasource.db2")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create()
            .driverClassName(driverClassName)
            .url(url)
            .username(username)
            .password(password)
            .build();
    }

    @Bean(name = "smsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
        @Qualifier("smsDataSource") DataSource dataSource,
        EntityManagerFactoryBuilder builder) {
        return builder
            .dataSource(dataSource)
            .packages("com.example.multidatabase.sms.entity")
            .build();
    }

    @Bean(name = "smsTransactionManager")
    public PlatformTransactionManager smsTransactionManager(
        @Qualifier("smsEntityManagerFactory") LocalContainerEntityManagerFactoryBean smsEntityManagerFactory) {
        return new JpaTransactionManager(
            Objects.requireNonNull(smsEntityManagerFactory.getObject()));
    }
}
