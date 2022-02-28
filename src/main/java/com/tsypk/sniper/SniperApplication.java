package com.tsypk.sniper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class SniperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SniperApplication.class, args);
    }
}
