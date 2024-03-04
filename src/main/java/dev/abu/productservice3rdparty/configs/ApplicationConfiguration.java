package dev.abu.productservice3rdparty.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders createHttpHeader(){
        return new HttpHeaders();
    }
}
