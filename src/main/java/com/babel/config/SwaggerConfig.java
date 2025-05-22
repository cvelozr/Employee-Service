package com.babel.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Employee Manager", version = "1.0.0", description = "This microservice is for babel test"))
public class SwaggerConfig {


}
