package com.example.multidatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.multidatabase.topic.entity",
    "com.example.multidatabase.sms.entity"})
public class UsingMultiDatabaseProofOfConceptsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsingMultiDatabaseProofOfConceptsApplication.class, args);
    }

}
