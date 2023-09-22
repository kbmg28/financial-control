package br.com.kbmg.financialcontrol.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPI3Config {

    @Value("${description-application}")
    private String descriptionApplication;

    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(getInfo())
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

    private Info getInfo() {
        return new Info()
                .version(buildProperties.getVersion())
                .description(descriptionApplication)
                .title(buildProperties.getName())
                .contact(getContact());
    }

    private Contact getContact() {
        return new Contact()
                .name("Kevin Gomes")
                .email("kb.developer.br@gmail.com")
                .url("https://www.linkedin.com/in/kevin-gomes-2b5b58175/");
    }
}