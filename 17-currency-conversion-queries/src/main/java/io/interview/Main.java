package io.interview;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ExchangeRate> rates = Arrays.asList(
                new ExchangeRate("USD", "EUR", 0.85),
                new ExchangeRate("EUR", "GBP", 0.88),
                new ExchangeRate("GBP", "INR", 105.0)
        );

        List<ConversionRequest> queries = Arrays.asList(
                new ConversionRequest("USD", "EUR", 100),
                new ConversionRequest("USD", "GBP", 100),
                new ConversionRequest("USD", "INR", 100),
                new ConversionRequest("USD", "JPY", 100)
        );

        CurrencyConverter converter = new CurrencyConverter();
        List<Double> results = converter.convertCurrency(rates, queries);

        System.out.println(results);
    }
}