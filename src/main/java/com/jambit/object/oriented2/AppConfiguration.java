package com.jambit.object.oriented2;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

    @Bean
    public RestTemplate myRestTemplate(final RestTemplateBuilder builder) {
        return builder.build();
    }
}
