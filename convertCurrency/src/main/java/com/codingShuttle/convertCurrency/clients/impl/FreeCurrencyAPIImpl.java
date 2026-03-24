package com.codingShuttle.convertCurrency.clients.impl;


import com.codingShuttle.convertCurrency.clients.FreeCurrencyAPIClient;
import com.codingShuttle.convertCurrency.dto.FreeCurrencyAPIDTO;
import com.codingShuttle.convertCurrency.exceptions.CurrencyInvalidException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;


import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Configuration
public class FreeCurrencyAPIImpl implements FreeCurrencyAPIClient {


    private final RestClient restClient;

    @Value("${freeCurrency.api.APIKey}")
    private String freeCurrencyAPIKey;


    Logger log = LoggerFactory.getLogger(FreeCurrencyAPIImpl.class);

    @Override
    public FreeCurrencyAPIDTO getConvertRate(String baseCurrency, String convertCurrency) {
        log.info("Calling getConvertRate");
        log.debug("baseCurrency:{}, convertCurrency:{}",baseCurrency, convertCurrency);
        System.out.println("API key:"+freeCurrencyAPIKey);
        String uri = "?apikey="+freeCurrencyAPIKey+"&currencies="+convertCurrency+"&base_currency="+baseCurrency;
        log.info("uri:{}",uri);
        try{
            FreeCurrencyAPIDTO freeCurrencyAPIDTO = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                        String errorBody = new String(response.getBody().readAllBytes());
                        log.error("getConvertRate() 4xx API Error: {}",errorBody);
//                        throw new RuntimeException("Could not get the Convert Rate by getConvertRate()");
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode rootNode = objectMapper.readTree(errorBody);
                        JsonNode errorsNode = rootNode.path("errors");

                        Map<String, List<String>> errors = objectMapper.convertValue(errorsNode,
                                new  TypeReference<Map<String, List<String>>>() {});
                        throw new CurrencyInvalidException("Invalid Currency parameter", HttpStatus.BAD_REQUEST, errors);
                    }))
                    .body(FreeCurrencyAPIDTO.class);
            log.debug("Successfully retrieved the currency rate by getConvertRate(): {}",freeCurrencyAPIDTO);
            Double convertRate = freeCurrencyAPIDTO.getData().get(convertCurrency);
            log.debug("Convert Rate by getConvertRate(): Convert Currency: {}, Convert Rate: {}",convertCurrency, convertRate);
            return freeCurrencyAPIDTO;
        }
        catch (CurrencyInvalidException e) {
            throw e;
        }
        catch(Exception e){
            log.error("Exception occurred while calling getConvertRate : {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
