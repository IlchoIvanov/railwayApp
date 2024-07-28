package com.example.railwayapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean
    public RestClient restClient(RailwayLinesApiConfig railwayLinesApiConfig) {
        return RestClient.builder().baseUrl(railwayLinesApiConfig.getBaseUrl()).
                defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).
                build();

    }

}
