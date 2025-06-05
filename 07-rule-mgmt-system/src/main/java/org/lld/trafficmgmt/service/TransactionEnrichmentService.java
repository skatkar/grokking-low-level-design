package org.lld.trafficmgmt.service;

/**
 * The intention of this class is to support multiple methods to enrich the transaction data
 */
public class TransactionEnrichmentService {
    /**
     * This block might call a different service or do the database query to see if the user is premium or not.
     * @param cardId
     * @return true - Premium user
     */
    public boolean isPremiumMember(String cardId) {
        // Logic goes here
        // return CustomerDB.isPremium(cardId);

        return true;
    }

    /**
     * This logic might call a different service or do the database query and compare with
     * today's date to see if it is a customer's birthday or not.
     * @param cardId
     * @return true - Today is the customer's birthday
     */
    public boolean isCustomerBirthday(String cardId) {
        //LocalDate birthday = CustomerDB.getDOB(cardId);
        //return LocalDate.now().equals(birthday);

        return true;
    }

    /**
     * Method to check whether a transaction is for a specific category or not
     * @param transactionId
     * @param category
     * @return true - transaction includes a specific category
     */
    public boolean hasItemFromCategory(String transactionId, String category) {
        // Lookup purchased items and check categories
        // List<String> itemCategories = OrderService.getItemCategories(transactionId);
        // return itemCategories.contains(category);
        return true;
    }

    /**
     * A check to see if the merchant is in a VIP category or not
     * @param merchantId
     * @return true - if the merchant is in a VIP category
     */
    public boolean isVip(String merchantId) {
        // Lookup merchant profile
        // return MerchantDB.isVipMerchant(merchantId);
        return true;
    }
}
