package com.codingShuttle.convertCurrency.services;


import com.codingShuttle.convertCurrency.advices.APIResponse;
import com.codingShuttle.convertCurrency.clients.FreeCurrencyAPIClient;
import com.codingShuttle.convertCurrency.dto.ConvertCurrencyDTO;
import com.codingShuttle.convertCurrency.dto.FreeCurrencyAPIDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConvertCurrencyService {

    Logger log = LoggerFactory.getLogger(ConvertCurrencyService.class);

    @Autowired
    private FreeCurrencyAPIClient freeCurrencyAPIClient;

    @Autowired
    private ModelMapper modelMapper;

    public APIResponse<ConvertCurrencyDTO> convertCurrencyWithUnits(String fromCurrency, String toCurrency, Integer units) {
        log.info("Calling freeCurrencyAPIClient by using convertCurrencyWithUnits()");
        log.debug("fromCurrency:{} toCurrency:{} units:{}",fromCurrency,toCurrency, units);
        FreeCurrencyAPIDTO freeCurrencyAPIDTO = freeCurrencyAPIClient.getConvertRate(fromCurrency,toCurrency);
        Double convertRate = freeCurrencyAPIDTO.getData().get(toCurrency);
        log.debug("Convert Currency: {} , Convert Rate: {}" , toCurrency, convertRate);
        double unitsPrice = convertRate*units;
        log.debug("Convert Rate for: {} ,units: Rs.{}", units, unitsPrice );
//        System.out.println("Convert Currency: " + toCurrency +", Convert Rate: " +convertRate);
//        System.out.println("Convert Rate for: " + units +" units: Rs." +unitsPrice );
        log.info("Units price calculated successfully by using convertCurrencyWithUnits()");


        return new APIResponse<>(new ConvertCurrencyDTO(fromCurrency, toCurrency,convertRate, units, unitsPrice));
    }
}
