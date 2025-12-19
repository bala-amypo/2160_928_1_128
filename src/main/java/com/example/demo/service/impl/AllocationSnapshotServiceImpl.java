package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.repository.*;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    private final AllocationSnapshotRecordRepository snapshotRepo;
    private final HoldingRecordRepository holdingRepo;
    private final AssetClassAllocationRuleRepository ruleRepo;
    private final RebalancingAlertRecordRepository alertRepo;

    public AllocationSnapshotServiceImpl(
            AllocationSnapshotRecordRepository snapshotRepo,
            HoldingRecordRepository holdingRepo,
            AssetClassAllocationRuleRepository ruleRepo,
            RebalancingAlertRecordRepository alertRepo
    ) {
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

        if (total <= 0) return null;

        Map<String, Double> allocation = holdings.stream()
                .collect(Collectors.groupingBy(
                        h -> h.getAssetClass().name(),
                        Collectors.summingDouble(HoldingRecord::getCurrentValue)
                ));

        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord();
        snapshot.setInvestorId(investorId);
        snapshot.setTotalPortfolioValue(total);
        snapshot.setAllocationJson(allocation.toString());

        snapshotRepo.save(snapshot);

        List<AssetClassAllocationRule> rules = ruleRepo.findActiveRulesHql(investorId);

        for (AssetClassAllocationRule rule : rules) {
            double value = allocation.getOrDefault(rule.getAssetClass().name(), 0.0);
            double currentPct = (value / total) * 100;

            if (currentPct > rule.getTargetPercentage()) {
                RebalancingAlertRecord alert = new RebalancingAlertRecord();
                alert.setInvestorId(investorId);
                alert.setAssetClass(rule.getAssetClass());
                alert.setCurrentPercentage(currentPct);
                alert.setTargetPercentage(rule.getTargetPercentage());
                alert.setSeverity(AlertSeverity.HIGH);
                alert.setMessage("Rebalancing required");

                alertRepo.save(alert);
            }
        }
        return snapshot;
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Snapshot not found"));
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
