package com.mylearning.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Account Microservice Rest API Documentation",
                description="Back Account microservice RestAPI Documentation details",
                version = "v1",
                termsOfService = "https://prashantkrt.github.io/",
                contact = @Contact(
                        name="Prashant",
                        url = "https://prashantkrt.github.io/",
                        email = "abc@abcd.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://prashantkrt.github.io/",
                        identifier = "Apache"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description="Back Account microservice RestAPI Documentation details",
                url = "https://prashantkrt.github.io/"
        )
)
public class AccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
