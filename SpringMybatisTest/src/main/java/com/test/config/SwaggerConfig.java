package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())   //apiInfo() 增加API相关信息
                .pathMapping("/")
                .select()    //通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现
                .paths(PathSelectors.regex("/.*"))  //匹配
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("UserManager service API")
                 .contact(new Contact("yang","","13716812206@163.com"))  //作者
                .description("this is UserManager service API")
                .version("1.0.0")
                .build();
    }
}
