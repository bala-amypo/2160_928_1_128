package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AllocationSnapshotServiceImpl {
    private final AllocationSnapshotRecordRepository snapshotRepository;
    private final HoldingRecordRepository holdingRepository;
    private final AssetClassAllocationRuleRepository ruleRepository;
    private final RebalancingAlertRecordRepository alertRepository;

    // Strict constructor order for Test Dependency Injection
    public AllocationSnapshotServiceImpl(AllocationSnapshotRecordRepository snapshotRepository,
                                         HoldingRecordRepository holdingRepository,
                                         AssetClassAllocationRuleRepository ruleRepository,
                                         RebalancingAlertRecordRepository alertRepository) {
        this.snapshotRepository = snapshotRepository;
        this.holdingRepository = holdingRepository;
        this.ruleRepository = ruleRepository;
        this.alertRepository = alertRepository;
    }

    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdingRepository.findByInvestorId(investorId);
        if (holdings.isEmpty()) {
            throw new IllegalArgumentException("No holdings found for investor");
        }

        double totalValue = holdings.stream().mapToDouble(HoldingRecord::getCurrentValue).sum();

        // 1. Save Snapshot
        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord(
                investorId, LocalDateTime.now(), totalValue, "{}" // Simplified JSON for demo
        );
        AllocationSnapshotRecord savedSnapshot = snapshotRepository.save(snapshot);

        // 2. Check Rules
        List<AssetClassAllocationRule> rules = ruleRepository.findByInvestorIdAndActiveTrue(investorId);
        
        Map<com.example.demo.entity.enums.AssetClassType, Double> valueByAsset = holdings.stream()
                .collect(Collectors.groupingBy(HoldingRecord::getAssetClass, 
                        Collectors.summingDouble(HoldingRecord::getCurrentValue)));

        for (AssetClassAllocationRule rule : rules) {
            double assetValue = valueByAsset.getOrDefault(rule.getAssetClass(), 0.0);
            double currentPct = (assetValue / totalValue) * 100.0;

            if (currentPct > rule.getTargetPercentage()) {
                RebalancingAlertRecord alert = new RebalancingAlertRecord(
                        investorId,
                        rule.getAssetClass(),
                        currentPct,
                        rule.getTargetPercentage(),
                        determineSeverity(currentPct, rule.getTargetPercentage()),
                        "Rebalancing required",
                        LocalDateTime.now(),
                        false
                );
                alertRepository.save(alert);
            }
        }

        return savedSnapshot;
    }
    
    private AlertSeverity determineSeverity(double current, double target) {
        double diff = current - target;
        if (diff > 20) return AlertSeverity.HIGH;
        if (diff > 10) return AlertSeverity.MEDIUM;
        return AlertSeverity.LOW;
    }

    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Snapshot not found: " + id));
    }

    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepository.findAll();
    }
}