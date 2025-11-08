package com.divide_ai.backend_divide_ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;


@SpringBootApplication
//http://localhost:8080/swagger-ui/index.html
//http://localhost:8080/v3/api-docs

// @Import(DynamoDbTableCreator.class) // Desabilitado temporariamente pra testar um trem
@OpenAPIDefinition(
    info = @Info(
        title = "DivideAi API",
        version = "0.1.0",
        description = "API para repartição de despesa",
        contact = @Contact(
            name = "Divide-Ai",
            url = "https://youtu.be/dQw4w9WgXcQ?si=ARVmuzzdz6sISxck"
        )
    )
)
public class DivideAiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DivideAiApplication.class, args);
    }

}



