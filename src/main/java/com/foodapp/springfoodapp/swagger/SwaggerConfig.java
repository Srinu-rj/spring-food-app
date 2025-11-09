package com.foodapp.springfoodapp.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Restaurant Application")
						.description("Backend APIs for E-Commerce app")
						.version("v1.0.0").contact(new Contact().name("srinu")
								.url("https://github.com/Srinu-rj")
								.email("dnsrinu143@gmail.com"))
						.summary("9642266933")
						.license(new License().name("License")
								.url("/"))).externalDocs(new ExternalDocumentation()
						.description("E-Commerce App Documentation")
						.url("http://localhost:8998/swagger-ui/index.html"));
	}

}
