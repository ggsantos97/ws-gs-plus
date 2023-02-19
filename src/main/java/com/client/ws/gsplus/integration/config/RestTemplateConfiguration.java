package com.client.ws.gsplus.integration.config;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor
@Configuration
public class RestTemplateConfiguration {


    @Value("${webservices.raspay.host}")
    private String serverHost;

    @Bean
    public RestTemplate restTemplateGetwayService(HeaderRequestInterceptor headerRequestInterceptor) {
        return new RestTemplateBuilder()
                .rootUri(serverHost)
                .additionalInterceptors(headerRequestInterceptor)
                .build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
