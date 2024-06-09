package com.urfu.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class TestingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestingApplication.class, args);
    }

}
