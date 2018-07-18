package com.cn.taskManager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Evan
 * @version 1.0
 * @date 2017/9/8 17:45
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置跨域访问
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}
