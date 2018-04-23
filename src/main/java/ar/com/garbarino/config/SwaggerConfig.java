package ar.com.garbarino.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by alejandro on 23/04/18.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket testApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("ar.com.garbarino.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData(){
        ApiInfo apiInfo = new ApiInfo(
                "API Shopping Cart",
                "API Shopping Cart",
                "1.0",
                "Terms of Service",
                new Contact("Alejandro Sánchez",
                        "url",
                        "sanchezalejandro86@gmail.com"),
                "Apache License version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0"
        );

        return apiInfo;
    }
}
