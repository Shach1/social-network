package ru.learning.socialmedia.configutarion;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Cal Net System Api",
                description = "API социальной сети Cal Net",
                version = "0.0.1",
                contact = @Contact(
                        name = "Трухманов Евгений",
                        email = "79169380904@mail.ru"
                )
        )
)
public class OpenApiConfig {
}
