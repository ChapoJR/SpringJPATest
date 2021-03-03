package com.nobelsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class SpringJpaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaTestApplication.class, args);
    }

    @Bean
    public Function<String, String> reverse() {
        return (input) -> new StringBuilder(input).reverse().toString();
    }

}
