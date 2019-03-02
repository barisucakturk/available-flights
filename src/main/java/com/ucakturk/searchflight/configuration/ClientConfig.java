package com.ucakturk.searchflight.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Data
@Configuration
public class ClientConfig {

    @Value("${client-service.url}")
    private String clientUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.create(clientUrl);
    }
}
