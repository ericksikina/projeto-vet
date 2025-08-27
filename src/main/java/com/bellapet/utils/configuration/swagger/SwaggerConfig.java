package com.bellapet.utils.configuration.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Bellapet").version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(
                        new Components()
                                .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                )
                )
                .tags(Arrays.asList(
                        new Tag().name("Administrador").description("Gerencia endpoints relacionados ao administrador"),
                        new Tag().name("Animal").description("Gerencia endpoints relacionados ao animal"),
                        new Tag().name("Tipo produto").description("Gerencia endpoints relacionados ao tipo do produto"),
                        new Tag().name("Serviço").description("Gerencia endpoints relacionados ao serviço"),
                        new Tag().name("Produto").description("Gerencia endpoints relacionados ao produto"),
                        new Tag().name("Pedido").description("Gerencia endpoints relacionados ao pedido"),
                        new Tag().name("Horário").description("Gerencia endpoints relacionados ao horário"),
                        new Tag().name("Endereço").description("Gerencia endpoints relacionados ao endereço"),
                        new Tag().name("Cliente").description("Gerencia endpoints relacionados ao cliente"),
                        new Tag().name("Carrinho").description("Gerencia endpoints relacionados ao carrinho"),
                        new Tag().name("Authorization").description("Gerencia endpoints relacionados ao authorization"),
                        new Tag().name("Agendamento").description("Gerencia endpoints relacionados ao agendamento")
                ));
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            openApi.getPaths()
                    .values()
                    .stream()
                    .flatMap(pathItem -> pathItem.readOperations().stream())
                    .forEach(operation -> {
                        ApiResponses responses = operation.getResponses();

                        ApiResponse apiResponseNaoEncontrado = new ApiResponse().description("Recurso não encontrado");
                        ApiResponse apiResponseErroInterno = new ApiResponse().description("Erro interno no servidor");

                        responses.addApiResponse("404", apiResponseNaoEncontrado);
                        responses.addApiResponse("500", apiResponseErroInterno);
                    });
        };
    }
}
