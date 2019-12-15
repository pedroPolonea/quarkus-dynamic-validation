package br.com.config;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
        title = "Swagger Order API", description = "Documentação dos endpoint's da aplicação", version = "0.0.1"))
public class OpenApiConfig extends Application {

}
