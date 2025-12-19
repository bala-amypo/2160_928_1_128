package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.AllocationSnapshotRecord;

public interface AllocationSnapshotService {
    AllocationSnapshotRecord computeSnapshot(Long investorId);
    Optional<AllocationSnapshotRecord> getSnapshotById(Long id);
    List<AllocationSnapshotRecord> getSnapshotsByInvestor(Long investorId);
}
