package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {

    private final AssetClassAllocationRuleRepository repository;

    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository repository) {
        this.repository = repository;
    }

    private void validate(Double pct) {
        if (pct < 0 || pct > 100) {
            throw new IllegalArgumentException("between 0 and 100");
        }
    }

    @Override
    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        validate(rule.getTargetPercentage());
        return repository.save(rule);
    }

    @Override
    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule rule) {
        AssetClassAllocationRule existing = getRuleById(id);
        validate(rule.getTargetPercentage());

        existing.setAssetClass(rule.getAssetClass());
        existing.setTargetPercentage(rule.getTargetPercentage());
        existing.setActive(rule.getActive());

        return repository.save(existing);
    }

    @Override
    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }

    @Override
    public List<AssetClassAllocationRule> getActiveRules(Long investorId) {
        return repository.findActiveRulesHql(investorId);
    }

    @Override
    public AssetClassAllocationRule getRuleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }
}
