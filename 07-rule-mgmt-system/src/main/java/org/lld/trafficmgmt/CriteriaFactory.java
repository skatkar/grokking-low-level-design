package org.lld.trafficmgmt;

public class CriteriaFactory {

    // Amount greater than check
    public static Criteria amountGreaterThan(double amount) {
        return new Criteria(txn -> txn.getAmount() > amount);
    }

    // Checking the merchant category
    public static Criteria merchantCategory(String category) {
        return new Criteria(txn -> category.equals(txn.getMerchantCategoryCode()));
    }

    // Country mismatch check
    // comparing transaction country code with the card's home country
    public static Criteria countryMismatch() {
        return new Criteria(txn -> !txn.getCountryCode().equals(txn.getCardHomeCountry()));
    }

    public static Criteria and(Criteria c1, Criteria c2){
        return c1.and(c2);
    }

    public static Criteria or(Criteria c1, Criteria c2) {
        return c1.or(c2);
    }
}
