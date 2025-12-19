package com.example.demo.repository;

import java.util.*;
import com.example.demo.entity.RebalancingAlertRecord;

public class RebalancingAlertRepository {
    private Map<Long, RebalancingAlertRecord> db = new HashMap<>();

    public RebalancingAlertRecord save(RebalancingAlertRecord a) {
        db.put(a.getId(), a);
        return a;
    }

    public Optional<RebalancingAlertRecord> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    public List<RebalancingAlertRecord> findAll() {
        return new ArrayList<>(db.values());
    }
}
