package com.divide_ai.backend_divide_ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;

import com.divide_ai.backend_divide_ai.config.DynamoDbTableCreator;


//Commitar essa parte depois
/* 
@SpringBootApplication
<<<<<<< HEAD
@import({DynamoDbTableCreator.java.example})
@OpenAPIDefinition(info = @info("DivideAi API", version = "0.1.0", description = "API para repartição de despesa", 
    repository = @repository(name = "Divide-Ai", url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ") ) )
*/






=======
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
>>>>>>> 17b5e31b511e7f609a183b81dfdc83e7d14b8520
public class DivideAiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DivideAiApplication.class, args);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 17b5e31b511e7f609a183b81dfdc83e7d14b8520
