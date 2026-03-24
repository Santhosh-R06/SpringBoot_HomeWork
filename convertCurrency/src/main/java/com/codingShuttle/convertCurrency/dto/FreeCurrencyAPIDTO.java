package com.codingShuttle.convertCurrency.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FreeCurrencyAPIDTO {

    private Map<String,Double> data;
}
