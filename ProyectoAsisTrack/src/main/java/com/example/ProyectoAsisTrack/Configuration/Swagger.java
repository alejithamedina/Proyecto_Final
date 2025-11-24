package com.example.ProyectoAsisTrack.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Proyecto AsisTrack")
                        .description("Documentación de la API para el sistema de control de asistencia y gestión académica")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo - AsisTrack")
                                .email("soporte@asistrack.com")
                                .url("https://asistrack.edu.co")));
    }
}