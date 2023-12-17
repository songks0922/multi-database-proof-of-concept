package com.example.multidatabase.config.database;

import java.util.Objects;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.multidatabase.topic.repository",
    entityManagerFactoryRef = "topicEntityManagerFactory",
    transactionManagerRef = "topicTransactionManager")
public class TopicDataSourceConfig {

    @Value("${spring.datasource.db1.url}")
    private String url;

    @Value("${spring.datasource.db1.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.db1.username}")
    private String username;

    @Value("${spring.datasource.db1.password}")
    private String password;

    @Primary
    @Bean(name = "topicDataSource")
    @ConfigurationProperties("spring.datasource.db1")
    public DataSource db1DataSource() {
        return DataSourceBuilder.create()
            .driverClassName(driverClassName)
            .url(url)
            .username(username)
            .password(password)
            .build();
    }

    @Primary
    @Bean(name = "topicEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(
        @Qualifier("topicDataSource") DataSource dataSource,
        EntityManagerFactoryBuilder builder) {
        return builder
            .dataSource(dataSource)
            .packages("com.example.multidatabase.topic.entity")
            .build();
    }

    @Primary
    @Bean(name = "topicTransactionManager")
    public PlatformTransactionManager db1TransactionManager(
        @Qualifier("topicEntityManagerFactory") LocalContainerEntityManagerFactoryBean db1EntityManagerFactory) {
        return new JpaTransactionManager(
            Objects.requireNonNull(db1EntityManagerFactory.getObject()));
    }
}
