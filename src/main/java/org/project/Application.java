package org.project;

import org.project.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({ApplicationProperties.class})
@ComponentScan(basePackages = { "org.project","org.project.controller"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}