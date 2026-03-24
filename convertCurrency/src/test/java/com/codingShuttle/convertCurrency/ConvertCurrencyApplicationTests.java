package com.codingShuttle.convertCurrency;

import com.codingShuttle.convertCurrency.clients.FreeCurrencyAPIClient;
import com.codingShuttle.convertCurrency.dto.FreeCurrencyAPIDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConvertCurrencyApplicationTests {

	@Autowired
	private FreeCurrencyAPIClient freeCurrencyAPIClient;

	@Test
	void contextLoads() {
	}

	@Test
	public void convertCurrency() {

	}

	@Test
	void getFreeCurrencyAPI() {
		String baseCurrency = "INRE";
		String convertCurrency = "USDE";
		Integer units = 500;
		FreeCurrencyAPIDTO freeCurrencyAPIDTO = freeCurrencyAPIClient.getConvertRate(baseCurrency,convertCurrency);
		Double convertRate = freeCurrencyAPIDTO.getData().get(convertCurrency);
		//log.debug("Convert Rate by getConvertRate(): Convert Currency: {}, Convert Rate: {}",convertCurrency, convertRate);
		double unitPrice = convertRate*units;
		System.out.println("Convert Currency: " + convertCurrency +", Convert Rate: " +convertRate);
		System.out.println("Convert Rate for: " + units +" units: Rs." +unitPrice );
	}
}
