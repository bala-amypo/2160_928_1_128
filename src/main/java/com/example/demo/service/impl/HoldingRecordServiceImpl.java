package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.HoldingRecord;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.service.HoldingRecordService;

@Service
public class HoldingRecordServiceImpl implements HoldingRecordService {

    @Autowired
    private HoldingRecordRepository repo;

    @Override
    public HoldingRecord saveHolding(HoldingRecord holding) {
        return repo.save(holding);
    }

    @Override
    public Optional<HoldingRecord> getHoldingById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }
}
