package com.investment.config;

import com.investment.interceptor.LogInterceptor;
import com.investment.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MvcConfig
 *
 * @author Sun
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public LogInterceptor setBean() {
        // 注入spring
        return new LogInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/", "/login");
        registry.addInterceptor(setBean()).addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/", "/login");
    }
}
