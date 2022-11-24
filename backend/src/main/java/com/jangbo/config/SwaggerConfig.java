package com.jangbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//swagger address : https://i7a602.p.ssafy.io:8080/swagger-ui/#/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
        @Bean
        public Docket swagger() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .ignoredParameterTypes(java.sql.Date.class)
                    .forCodeGeneration(true)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(apiInfo())
                    .enable(true);
        }

        private ApiInfo apiInfo(){
            return new ApiInfoBuilder()
                    .title("장보장 테스트 API 타이틀")
                    .description("장보장 관련 테스트 API 상세소개 및 사용법")
                    .version("1.0")
                    .build();
        }

    }

