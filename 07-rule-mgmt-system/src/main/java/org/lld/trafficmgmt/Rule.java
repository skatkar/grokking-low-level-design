package org.lld.trafficmgmt;

public class Rule {
    String name;
    Criteria criteria;

    public Rule(String name, Criteria criteria) {
        this.name = name;
        this.criteria = criteria;
    }

    public boolean evaluate(Transaction txn) {
        return criteria.evaluate(txn);
    }

    public String getName(){
        return name;
    }
}
