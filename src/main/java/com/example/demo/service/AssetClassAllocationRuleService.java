package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.AssetClassAllocationRule;

public interface AssetClassAllocationRuleService {
    AssetClassAllocationRule saveRule(AssetClassAllocationRule rule);
    Optional<AssetClassAllocationRule> getRuleById(Long id);
    List<AssetClassAllocationRule> getRulesByInvestor(Long investorId);
    List<AssetClassAllocationRule> getActiveRules(Long investorId);
}
