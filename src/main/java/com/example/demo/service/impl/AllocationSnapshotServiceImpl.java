package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.AllocationSnapshotService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
            throw new IllegalArgumentException("No holdings for investor");
        }

        double total = holdings.stream()
                .mapToDouble(HoldingRecord::getCurrentValue)
                .sum();

        if (total <= 0) {
            throw new IllegalArgumentException("Total portfolio value must be > 0");
        }

        Map<AssetClassType, Double> allocation = holdings.stream()
                .collect(Collectors.groupingBy(
                        HoldingRecord::getAssetClass,
                        Collectors.summingDouble(HoldingRecord::getCurrentValue)
                ));

        Map<AssetClassType, Double> percentages = new HashMap<>();
        allocation.forEach((k, v) ->
                percentages.put(k, (v / total) * 100)
        );

        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord(
                investorId,
                LocalDateTime.now(),
                total,
                percentages.toString()
        );

        snapshotRepo.save(snapshot);

        List<AssetClassAllocationRule> rules =
                ruleRepo.findByInvestorIdAndActiveTrue(investorId);

        for (AssetClassAllocationRule rule : rules) {
            double currentPct = percentages.getOrDefault(rule.getAssetClass(), 0.0);
            if (currentPct > rule.getTargetPercentage()) {
                RebalancingAlertRecord alert = new RebalancingAlertRecord(
                        investorId,
                        rule.getAssetClass(),
                        currentPct,
                        rule.getTargetPercentage(),
                        AlertSeverity.MEDIUM,
                        "Rebalancing required",
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
                .orElseThrow(() -> new ResourceNotFoundException("Snapshot not found with id " + id));
    }

    @Override
    public List<AllocationSnapshotRecord> getSnapshotsByInvestor(Long investorId) {
        return snapshotRepo.findAll().stream()
                .filter(s -> s.getInvestorId().equals(investorId))
                .toList();
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepo.findAll();
    }
}
