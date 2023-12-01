package com.gws.api.apigws;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API GWS", version = "1.5.0",description = "EndPoints da Gestão Workload Squads"))
public class ApiGwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGwsApplication.class, args);
	}

}
