package org.lld.trafficmgmt;

import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        Transaction transaction = Transaction.builder()
                .id("1234HASD")
                .amount(5200)
                .cardHomeCountry("IN")
                .countryCode("US")
                .currency("USD")
                .merchantId("MER12443")
                .timestamp(Instant.parse("2023-05-15T14:30:45Z"))
                .merchantCategoryCode("132443432")
                .cardId("card_123")
                .build();


        RuleManager ruleManager = new RuleManager();

        // Scenario 1
        System.out.println("Validating whether the first scenario is valid or not - " + ruleManager.scenario1(transaction));

        // Scenario 2
        transaction.setAmount(200);
        System.out.println("Validating whether the second scenario is valid or not (= 200) - " + ruleManager.scenario2(transaction));
        transaction.setAmount(210);
        System.out.println("Validating whether the second scenario is valid or not (> 200) - " + ruleManager.scenario2(transaction));

        // Scenario 3
        System.out.println("Validating whether the third scenario is valid or not - " + ruleManager.scenario3(transaction));
    }
}