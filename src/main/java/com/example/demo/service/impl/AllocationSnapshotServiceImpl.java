package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    private final AllocationSnapshotRecordRepository snapshotRecordRepository;
    private final HoldingRecordRepository holdingRecordRepository;
    private final AssetClassAllocationRuleRepository allocationRuleRepository;
    private final RebalancingAlertRecordRepository alertRecordRepository;

    public AllocationSnapshotServiceImpl(
            AllocationSnapshotRecordRepository snapshotRecordRepository,
            HoldingRecordRepository holdingRecordRepository,
            AssetClassAllocationRuleRepository allocationRuleRepository,
            RebalancingAlertRecordRepository alertRecordRepository) {
        this.snapshotRecordRepository = snapshotRecordRepository;
        this.holdingRecordRepository = holdingRecordRepository;
        this.allocationRuleRepository = allocationRuleRepository;
        this.alertRecordRepository = alertRecordRepository;
    }

    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdingRecordRepository.findByInvestorId(investorId);
        if (holdings.isEmpty()) {
            throw new IllegalArgumentException("No holdings found for investor: " + investorId);
        }

        double totalValue = holdings.stream()
                .mapToDouble(HoldingRecord::getCurrentValue)
                .sum();

        List<AssetClassAllocationRule> rules = allocationRuleRepository.findByInvestorIdAndActiveTrue(investorId);
        
        // Create snapshot
        String allocationData = "{}"; // Simplified JSON
        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord(investorId, LocalDateTime.now(), totalValue, allocationData);
        snapshotRecordRepository.save(snapshot);

        // Check for rebalancing alerts
        checkRebalancingAlerts(investorId, holdings, rules);

        return snapshot;
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Snapshot", "id", id));
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRecordRepository.findAll();
    }

    private void checkRebalancingAlerts(Long investorId, List<HoldingRecord> holdings, List<AssetClassAllocationRule> rules) {
        double totalValue = holdings.stream().mapToDouble(HoldingRecord::getCurrentValue).sum();
        
        for (AssetClassAllocationRule rule : rules) {
            double currentPercentage = holdings.stream()
                    .filter(h -> h.getAssetClass() == rule.getAssetClass())
                    .mapToDouble(HoldingRecord::getCurrentValue)
                    .sum() / totalValue * 100;
            
            if (currentPercentage > rule.getTargetPercentage()) {
                AlertSeverity severity = currentPercentage > rule.getTargetPercentage() + 10 ? 
                    AlertSeverity.HIGH : AlertSeverity.MEDIUM;
                
                RebalancingAlertRecord alert = new RebalancingAlertRecord(
                    investorId, rule.getAssetClass(), currentPercentage, 
                    rule.getTargetPercentage(), severity, 
                    "Rebalancing needed", LocalDateTime.now(), false
                );
                alertRecordRepository.save(alert);
            }
        }
    }
}
