package io.interview;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Salary timeline for a given user
 */
public class SalaryTimeline {
    private final TreeMap<LocalDate, SalaryChange> record = new TreeMap<>();

    public void insert(SalaryChange salaryChange) {
        SalaryChange existing = record.get(salaryChange.getEffectiveOn());
        if(existing != null && existing.getChangeId().equals(salaryChange.getChangeId())) {
            throw new PayrollException.SalaryChangeConflict("Conflicting salary change for " + salaryChange.getChangeId());
        }

        record.put(salaryChange.getEffectiveOn(), salaryChange);
    }

    public void remove(SalaryChange salaryChange) {
        SalaryChange existing = record.get(salaryChange.getEffectiveOn());
        if(existing == null) {
            throw new PayrollException.SalaryChangeNotFound("Salary changes do not exist for " + salaryChange.getChangeId());
        }

        if(existing.getChangeId().equals(salaryChange.getChangeId())) {
            record.remove(salaryChange.getEffectiveOn());
        }
    }

    public Money salaryAsOf(LocalDate asOf){
        Map.Entry<LocalDate, SalaryChange> entry = record.floorEntry(asOf);
        return entry == null ?
                null :
                entry.getValue().getSalary();
    }

    public List<SalaryChange> activeChanges() {
        return new ArrayList<>(record.values());
    }
}
