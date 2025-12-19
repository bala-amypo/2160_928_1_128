package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {

    @Autowired
    private AssetClassAllocationRuleRepository repository;

    @Override
    public AssetClassAllocationRule getRuleById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Example additional method
    public AssetClassAllocationRule findActiveRules(Long investorId) {
        return repository.findActiveRules(investorId).stream().findFirst().orElse(null);
    }
}
