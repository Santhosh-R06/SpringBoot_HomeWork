package com.codingShuttle.convertCurrency.clients;

import com.codingShuttle.convertCurrency.dto.FreeCurrencyAPIDTO;

public interface FreeCurrencyAPIClient {

    FreeCurrencyAPIDTO getConvertRate(String baseCurrency, String convertCurrency);
}
