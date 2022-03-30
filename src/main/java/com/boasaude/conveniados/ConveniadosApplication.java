package com.boasaude.conveniados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Boa Saúde - Conveniados", version = "1.0", description = "Documentação do microsserviço de conveniados"))
public class ConveniadosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConveniadosApplication.class, args);
    }
}
