package com.uam.apiReservacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();

    Info info = new Info()
            .title("API PRINCIPAL (RESERVACION)")
            .version("1.0")
            .description("Esta api funciona como una BFF que conecta una pagina web hecha en angular y dos apis con su respectiva base de datos");

    return new OpenAPI().info(info);
  }
}
