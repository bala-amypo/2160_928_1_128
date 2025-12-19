package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.repository.AllocationSnapshotRecordRepository;
import com.example.demo.service.AllocationSnapshotService;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    @Autowired
    private AllocationSnapshotRecordRepository repo;

    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        AllocationSnapshotRecord record = new AllocationSnapshotRecord();
        record.setInvestorId(investorId);
        record.setTotalPortfolioValue(1000.0);
        record.setAllocationJson("{\"STOCKS\":50,\"BONDS\":50}");
        return repo.save(record);
    }

    @Override
    public Optional<AllocationSnapshotRecord> getSnapshotById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<AllocationSnapshotRecord> getSnapshotsByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }
}
