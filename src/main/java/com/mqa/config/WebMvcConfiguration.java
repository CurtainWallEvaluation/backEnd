package com.mqa.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                // 接口文档标题
                .info(new Info().title("幕墙评估系统接口文档")
                        // 接口文档简介
                        .description("这是基于Knife4j OpenApi3的接口文档")
                        // 接口文档版本
                        .version("v1.0")
                        // 开发者联系方式
                        .contact(new Contact().name("GrayPigeonHGH").email("2371849349@qq.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringBoot基础框架")
                        .url("http://127.0.0.1:8088"));
    }

}
