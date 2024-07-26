package com.example.railwayapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@EnableScheduling
public class RailwayAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RailwayAppApplication.class, args);
    }

}
