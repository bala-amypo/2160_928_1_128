package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.service.AssetClassAllocationRuleService;

@Service
public class AssetClassAllocationRuleServiceImpl implements AssetClassAllocationRuleService {

    @Autowired
    private AssetClassAllocationRuleRepository repo;

    @Override
    public AssetClassAllocationRule saveRule(AssetClassAllocationRule rule) {
        return repo.save(rule);
    }

    @Override
    public Optional<AssetClassAllocationRule> getRuleById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }

    @Override
    public List<AssetClassAllocationRule> getActiveRules(Long investorId) {
        return repo.findActiveRules(investorId);
    }
}
