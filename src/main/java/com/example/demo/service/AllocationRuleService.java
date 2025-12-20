package com.example.demo.service;

import com.example.demo.entity.AssetClassAllocationRule;
import java.util.List;

public interface AllocationRuleService {

    List<AssetClassAllocationRule> getAllRules();

    List<AssetClassAllocationRule> getActiveRules(Long investorId);
}
