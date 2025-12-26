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
        // Validation is handled in Entity Constructor/Setter according to test expectations, 
        // but Entity Constructor ensures logic passes before saving.
        return repository.save(rule);
    }

    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule updatedRule) {
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