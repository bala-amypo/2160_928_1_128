package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.HoldingRecord;

public interface HoldingRecordService {
    HoldingRecord saveHolding(HoldingRecord holding);
    Optional<HoldingRecord> getHoldingById(Long id);
    List<HoldingRecord> getHoldingsByInvestor(Long investorId);
}
