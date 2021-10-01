package com.pagewatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PagewatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagewatcherApplication.class, args);
    }
    
}
