package org.lld.trafficmgmt;

import org.lld.trafficmgmt.service.TransactionEnrichmentService;

public class RuleManager {
    TransactionEnrichmentService service;

    public RuleManager(){
        this.service = new TransactionEnrichmentService();
    }



    // Flag if transaction amount > $5,000 AND transaction country differs from card's home country
    public boolean scenario1(Transaction txn) {
        Criteria amtGt5k = CriteriaFactory.amountGreaterThan(5000);
        Criteria countryMismatch = CriteriaFactory.countryMismatch();

        Rule scenario1Rule = new Rule("GreaterThanAndCountryMismatch", CriteriaFactory.and(amtGt5k, countryMismatch));
        return scenario1Rule.evaluate(txn);
    }

    // Apply 10% discount if customer is a premium member AND purchase amount > $200
    public boolean scenario2(Transaction transaction) {
        Criteria premiumCustomer = new Criteria(txn -> service.isPremiumMember(txn.getCardId()));
        Criteria amtGt200 = CriteriaFactory.amountGreaterThan(200);

        Rule scenario2Rule = new Rule("PremiumAndGreaterThan200", CriteriaFactory.and(premiumCustomer, amtGt200));
        return scenario2Rule.evaluate(transaction);
    }

    // Apply 5% discount if purchase is from Electronics OR if it's customer’s birthday
    public boolean scenario3(Transaction transaction) {
        Criteria electronicPurchase = new Criteria(txn -> service.hasItemFromCategory(txn.getId(), "Electronics"));
        Criteria birthdayCriteria = new Criteria(txn -> service.isCustomerBirthday(txn.getCardId()));

        Rule scenario3Rule = new Rule("ElectronicsAndBirthday", CriteriaFactory.and(electronicPurchase, birthdayCriteria));

        return scenario3Rule.evaluate(transaction);
    }
}
