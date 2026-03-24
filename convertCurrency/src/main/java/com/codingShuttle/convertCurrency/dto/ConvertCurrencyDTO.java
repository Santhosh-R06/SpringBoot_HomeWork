package com.codingShuttle.convertCurrency.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConvertCurrencyDTO {

    private String fromCurrency;
    private String toCurrency;
    private Double toCurrencyRate;
    private Integer units;
    private Double unitsPrice;
}
