package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AllocationRuleServiceImpl {
    private final AssetClassAllocationRuleRepository repository;

    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository repository) {
        this.repository = repository;
    }

    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        // Validation moved here
        if (rule.getTargetPercentage() < 0 || rule.getTargetPercentage() > 100) {
            throw new IllegalArgumentException("Target percentage must be between 0 and 100");
        }
        return repository.save(rule);
    }

    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule updatedRule) {
        // Validation moved here
        if (updatedRule.getTargetPercentage() < 0 || updatedRule.getTargetPercentage() > 100) {
            throw new IllegalArgumentException("Target percentage must be between 0 and 100");
        }

        AssetClassAllocationRule existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found: " + id));
        
        existing.setTargetPercentage(updatedRule.getTargetPercentage());
        existing.setAssetClass(updatedRule.getAssetClass());
        existing.setActive(updatedRule.getActive());
        
        return repository.save(existing);
    }

    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }
}