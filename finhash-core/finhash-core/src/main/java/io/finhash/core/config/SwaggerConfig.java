package io.finhash.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;


@EnableSwagger2WebFlux
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.finhash.core"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build();
    }

    /**
     * @return ApiInfo information about the web application.
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("FinHash Web Application API")
                .description("Contains all the CRUD operations of the FinHash platform.")
                .version("v0.1.0")
                .contact(new Contact("Chris Paraskeva", "www.infinittex.com", "chris@infinittex.com"))
                .build();
    }

}