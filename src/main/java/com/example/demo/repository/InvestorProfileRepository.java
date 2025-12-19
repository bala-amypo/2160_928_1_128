package com.example.demo.repository;

import java.util.*;
import com.example.demo.entity.InvestorProfile;

public class InvestorProfileRepository {
    private Map<Long, InvestorProfile> db = new HashMap<>();

    public InvestorProfile save(InvestorProfile i) {
        db.put(i.getId(), i);
        return i;
    }

    public Optional<InvestorProfile> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    public List<InvestorProfile> findAll() {
        return new ArrayList<>(db.values());
    }
}
