package com.example.nexus.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        SecurityConfiguration securityConfiguration = new SecurityConfiguration();
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-jwt", securityConfiguration.securityScheme()))
                .info(infoAPI())
                .addSecurityItem(new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
    }
    public Info infoAPI() {
        return new Info().title("USER Microservice")
                .description("This is a springboot exam");
    }

}