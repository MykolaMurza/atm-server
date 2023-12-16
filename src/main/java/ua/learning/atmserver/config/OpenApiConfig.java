package ua.learning.atmserver.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("ATM API Documentation")
                        .description("Description for ATM API.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Mykola MURZA")
                                .email("murza.na00@gmail.com")));
    }
}
