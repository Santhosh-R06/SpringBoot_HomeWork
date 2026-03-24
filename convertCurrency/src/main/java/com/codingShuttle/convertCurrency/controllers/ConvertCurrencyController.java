package com.codingShuttle.convertCurrency.controllers;


import com.codingShuttle.convertCurrency.advices.APIResponse;
import com.codingShuttle.convertCurrency.dto.ConvertCurrencyDTO;
import com.codingShuttle.convertCurrency.services.ConvertCurrencyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "/convertCurrency")
@RequiredArgsConstructor
public class ConvertCurrencyController {

    Logger log = LoggerFactory.getLogger(ConvertCurrencyController.class);
    private final ConvertCurrencyService convertCurrencyService;

//    @GetMapping
//    public Boolean convertCurrency() {
//        log.debug("Calling convertCurrency");
//        return true;
//    }

    @GetMapping
    public APIResponse<ConvertCurrencyDTO> convertCurrencyWithUnits(@RequestParam(defaultValue = "INR") @Pattern(regexp = "^[A-Z]{3}$", message = "toCurrency must be a 3-letter currency code (e.g. INR, USD)")  String fromCurrency,
                                                                   @RequestParam(defaultValue = "USD") @Pattern(regexp = "^[A-Z]{3}$", message = "toCurrency must be a 3-letter currency code (e.g. INR, USD)") String toCurrency,
                                                                   @RequestParam(defaultValue = "1")
                                                                        @Max(value = 99999, message = "Units must not exceed 99999")
                                                                        @Positive(message = "Units must be a positive value") Integer units) {
        return convertCurrencyService.convertCurrencyWithUnits(fromCurrency,toCurrency,units);
    }


}
