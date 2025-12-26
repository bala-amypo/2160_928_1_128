package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.AllocationSnapshotService;

import java.time.LocalDateTime;
import java.util.*;

public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    private final AllocationSnapshotRecordRepository snapshotRepo;
    private final HoldingRecordRepository holdingRepo;
    private final AssetClassAllocationRuleRepository ruleRepo;
    private final RebalancingAlertRecordRepository alertRepo;

    public AllocationSnapshotServiceImpl(
            AllocationSnapshotRecordRepository snapshotRepo,
            HoldingRecordRepository holdingRepo,
            AssetClassAllocationRuleRepository ruleRepo,
            RebalancingAlertRecordRepository alertRepo) {
        this.snapshotRepo = snapshotRepo;
        this.holdingRepo = holdingRepo;
        this.ruleRepo = ruleRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {

        List<HoldingRecord> holdings = holdingRepo.findByInvestorId(investorId);
        if (holdings.isEmpty()) {
            throw new IllegalArgumentException("No holdings");
        }

        double total = holdings.stream()
                .mapToDouble(HoldingRecord::getCurrentValue)
                .sum();

        if (total <= 0) {
            throw new IllegalArgumentException("Total must be > 0");
        }

        Map<String, Double> allocationMap = new HashMap<>();
        for (HoldingRecord h : holdings) {
            allocationMap.merge(
                    h.getAssetClass().name(),
                    h.getCurrentValue(),
                    Double::sum
            );
        }

        Map<String, Double> percentageMap = new HashMap<>();
        allocationMap.forEach((k, v) ->
                percentageMap.put(k, (v / total) * 100)
        );

        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord(
                investorId,
                LocalDateTime.now(),
                total,
                percentageMap.toString()
        );

        snapshotRepo.save(snapshot);

        List<AssetClassAllocationRule> rules =
                ruleRepo.findByInvestorIdAndActiveTrue(investorId);

        for (AssetClassAllocationRule rule : rules) {
            Double currentPct = percentageMap.get(rule.getAssetClass().name());
            if (currentPct != null && currentPct > rule.getTargetPercentage()) {

                RebalancingAlertRecord alert =
                        new RebalancingAlertRecord(
                                investorId,
                                rule.getAssetClass(),
                                currentPct,
                                rule.getTargetPercentage(),
                                AlertSeverity.MEDIUM,
                                "Rebalance required",
                                LocalDateTime.now(),
                                false
                        );
                alertRepo.save(alert);
            }
        }
        return snapshot;
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Snapshot not found: " + id));
    }

    @Override
    public List<AllocationSnapshotRecord> getSnapshotsByInvestor(Long investorId) {
        return snapshotRepo.findAll();
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepo.findAll();
    }
}
