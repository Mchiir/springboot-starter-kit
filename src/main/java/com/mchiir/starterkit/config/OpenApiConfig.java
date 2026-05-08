package com.mchiir.starterkit.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()

                .info(new Info()
                        .title("Spring Boot Starter Kit API")
                        .description("Production-ready REST API template using Spring Boot 3.5.x")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Backend Team")
                                .email("support@starterkit.com")
                                .url("https://github.com/mchiir/springboot-starter-kit"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development server"),
                        new Server()
                                .url("http://localhost:8080")
                                .description("Production server")
                ));
    }
}