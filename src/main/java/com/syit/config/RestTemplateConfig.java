package com.syit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//https://www.geeksforgeeks.org/spring-boot-rest-template/

@Configuration
public class RestTemplateConfig {

	//Bean Configuration for RestTemplate
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
