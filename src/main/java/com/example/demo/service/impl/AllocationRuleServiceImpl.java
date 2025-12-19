package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {

    @Autowired
    private AssetClassAllocationRuleRepository repository;

    // Implement abstract method from interface
    @Override
    public AssetClassAllocationRule getRuleById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Implement abstract method from interface
    @Override
    public List<AssetClassAllocationRule> getActiveRules(Long investorId) {
        return repository.findActiveRules(investorId);
    }
}
