package com.example.bankingapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {
    public OpenAPI myOPenAPI(){
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("URL in Dev environment");

        Contact contact = new Contact();
        contact.setEmail("catcandance2004@gmail.com");
        contact.setName("API Support");

        Info info = new Info()
                .title("Banking Management API")
                .version("1.0")
                .contact(contact)
                .description("The API exposes endpoints for managing banking ops.");

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}