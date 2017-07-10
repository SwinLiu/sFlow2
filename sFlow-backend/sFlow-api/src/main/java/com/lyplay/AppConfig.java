package com.lyplay;

import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public AppConfig() {
        // Implement ServletContextListener if this is set too late
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        logger.info("Time zone set to 'UTC'");
    }
}
