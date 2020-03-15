package com.distributedsystems.restfulserviceshomework.config;

import com.distributedsystems.restfulserviceshomework.config.log.RequestAndResponseLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FilterConfiguration {

    @Bean
    public RequestAndResponseLoggingFilter get() {
        return new RequestAndResponseLoggingFilter();
    }
}
