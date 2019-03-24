package com.alimin.parser;

import com.alimin.parser.entity.Parameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Parameter getSomeService(
            @Value("${accesslog}") String path,
            @Value("${startDate}") String startDate,
            @Value("${duration}") String duration,
            @Value("${threshold}") String threshold
    ) {
        return new Parameter(path,startDate,duration,threshold);
    }
}
