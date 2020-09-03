package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiinfo())
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test"))
                .build();


    }

    private ApiInfo apiinfo() {
        return new ApiInfoBuilder().title("接口文档")
                .contact(new Contact("liang", "", "204000457@qq.com"))
                .description("这是我的swagger接口文档")
                .version("1.0.0")
                .build();
    }

}
