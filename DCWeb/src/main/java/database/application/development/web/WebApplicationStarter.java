package database.application.development.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"database.application.development"})
public class WebApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(WebApplicationStarter.class, args);
    }

    @Bean
    public Docket dcsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo("Dry Cleaners API Documentation",
                        "Dry Cleaners Management API Documentation",
                        "1.0",
                        null,
                        new Contact("", "", ""),
                        null, null))
                .select()
                .apis(RequestHandlerSelectors.basePackage("database.application.development.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }
}
