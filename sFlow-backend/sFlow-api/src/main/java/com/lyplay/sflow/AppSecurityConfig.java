package com.lyplay.sflow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lyplay.sflow.api.interceptor.LoginInterceptor;

@Configuration
public class AppSecurityConfig {


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
            
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
            	registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api/**");
            	super.addInterceptors(registry);
            }
            
//            @Override
//            public void configure(WebSecurity web) throws Exception {
//                web.ignoring().antMatchers("/swagger-resources", "/swagger-ui.html");
//            }
        };
    }

}
