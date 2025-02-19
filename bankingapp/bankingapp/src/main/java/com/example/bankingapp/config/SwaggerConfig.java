package com.example.bankingapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI bankingAppOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Banking app API")
                        .version()
                        .description()
                        .contact(new Contact()
                                .name("Hien")
                                .email("")
                                .url())
                )
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth")).schemaRequirement("BearerAuth", securityScheme());

    }

    @Bean
    public SecurityScheme securityScheme() {
        return new SecurityScheme()
                .name("BearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .description("JWT auth for API access")
    }
}