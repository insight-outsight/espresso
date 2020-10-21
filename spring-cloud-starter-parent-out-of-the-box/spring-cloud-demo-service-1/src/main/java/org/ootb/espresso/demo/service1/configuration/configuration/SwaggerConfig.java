package org.ootb.espresso.demo.service1.configuration.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.doc.host}")
    private String host;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).host(host).apiInfo(getApiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.rcplatform.phoenix.configuration.interfaces.controllers"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("Phoenix Configuration RESTFUL APIs").description("api接口文档").version("1.0")
                .build();
    }

}
