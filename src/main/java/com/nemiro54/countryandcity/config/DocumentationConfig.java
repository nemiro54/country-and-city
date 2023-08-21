package com.nemiro54.countryandcity.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class DocumentationConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION)
        )
        .components(
            new Components().addSecuritySchemes(HttpHeaders.AUTHORIZATION,
                new SecurityScheme()
                    .in(In.HEADER)
                    .type(Type.HTTP)
                    .scheme("Bearer")
                    .bearerFormat("JWT")
            )
        )
        .info(new Info().title("Country and City Application API").version("1.0.0"));
  }
}
