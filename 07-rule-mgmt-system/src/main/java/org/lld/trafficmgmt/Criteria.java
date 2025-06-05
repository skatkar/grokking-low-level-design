package org.lld.trafficmgmt;

import java.util.function.Predicate;

public class Criteria {

    Predicate<Transaction> predicate;

    public Criteria(Predicate<Transaction> predicate){
        this.predicate = predicate;
    }

    public boolean evaluate(Transaction tx) {
        return predicate.test(tx);
    }


    // Functional composition for AND operation
    public Criteria and(Criteria other){
        return new Criteria(this.predicate.and(other.predicate));
    }

    // Functional composition for OR operation
    public Criteria or(Criteria other) {
        return new Criteria(this.predicate.or(other.predicate));
    }
}
