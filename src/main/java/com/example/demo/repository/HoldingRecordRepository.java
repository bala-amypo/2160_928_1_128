package com.example.demo.repository;

import java.util.*;
import com.example.demo.entity.HoldingRecord;

public class HoldingRecordRepository {
    private Map<Long, HoldingRecord> db = new HashMap<>();

    public HoldingRecord save(HoldingRecord h) {
        db.put(h.getId(), h);
        return h;
    }

    public Optional<HoldingRecord> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    public List<HoldingRecord> findAll() {
        return new ArrayList<>(db.values());
    }
}
