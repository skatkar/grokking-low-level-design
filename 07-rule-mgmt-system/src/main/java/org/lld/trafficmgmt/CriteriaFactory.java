package org.lld.trafficmgmt;

public class CriteriaFactory {
    public Criteria and(Criteria c1, Criteria c2){
        return c1.and(c2);
    }

    public Criteria or(Criteria c1, Criteria c2) {
        return c1.or(c2);
    }
}
