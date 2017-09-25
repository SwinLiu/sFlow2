package com.lyplay.sflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.lyplay.sflow.api.util.SpringUtil;

@SpringBootApplication
@Import(SpringUtil.class)
public class App extends SpringBootServletInitializer {
	public static void main( String[] args )  
    {  
        SpringApplication.run(App.class, args);  
    }  

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }
}
