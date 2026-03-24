package com.codingShuttle.convertCurrency.configs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class RestClientConfig {

    Logger log = LoggerFactory.getLogger(RestClientConfig.class);

    @Value("${freeCurrency.api.base.url}")
    private String freeCurrencyAPIBaseUrl;

//    @Value("$freeCurrency.api.APIKey")
//    private String freeCurrencyAPIKey;

    @Bean("freeCurrencyAPIRestClient")
    RestClient freeCurrencyAPIRestClient() {
        log.info("Calling freeCurrencyAPIRestClient");
//        log.info("baseCurrency:{} convertCurrency:{}", baseCurrency, convertCurrency);
        String baseUrl = freeCurrencyAPIBaseUrl; //+ "?apiKey=" + freeCurrencyAPIKey; //+ "&currencies=" + convertCurrency + "&base_currency=" + baseCurrency;
        log.info("baseUrl:{}", baseUrl);
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
                    String errorBody = new String(response.getBody().readAllBytes());
                    System.out.println("freeCurrencyAPI 4xx API Error: " + errorBody);
                    log.error("freeCurrencyAPI 4xx API Error: {}", errorBody);
                    throw new RuntimeException("freeCurrencyAPIRestClient API Error");
                })
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,(request, response) -> {
                    String errorBody = new String(response.getBody().readAllBytes());
                    System.out.println("freeCurrencyAPIRestClient 5xx Server Error" + errorBody);
                    log.error("freeCurrencyAPIRestClient 5xx Server Error : {}" , errorBody);
                    throw new RuntimeException("freeCurrencyAPIRestClient Server Error");
                })
                .build();
    }
}
