package com.cn.taskManager.config;

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

/**
 *
 * @author Evan
 * @date 2018/3/5
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Value("${swagger2.enable}")
    private Boolean enable;

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cn.taskManager"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("task-manager")
                .license("任务管理mycat")
                .version("1.0")
                .build();
    }
}
