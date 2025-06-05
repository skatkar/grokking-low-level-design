package org.lld.trafficmgmt;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Transaction {
    private String id;
    private double amount;
    private String currency;
    private String merchantId;
    private String merchantCategoryCode;
    private Instant timestamp;
    private String cardId;
    private String countryCode;
    private String cardHomeCountry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCardHomeCountry() {
        return cardHomeCountry;
    }

    public void setCardHomeCountry(String cardHomeCountry) {
        this.cardHomeCountry = cardHomeCountry;
    }
}
