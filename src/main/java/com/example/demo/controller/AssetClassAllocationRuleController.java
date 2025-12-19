package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.AssetClassAllocationRuleService;

@RestController
@RequestMapping("/api/allocation-rules")
public class AssetClassAllocationRuleController {

    @Autowired
    private AssetClassAllocationRuleService service;

    @PostMapping
    public AssetClassAllocationRule save(@RequestBody AssetClassAllocationRule rule) {
        return service.saveRule(rule);
    }

    @GetMapping("/{id}")
    public Optional<AssetClassAllocationRule> getRule(@PathVariable Long id) {
        return service.getRuleById(id);
    }

    @GetMapping("/investor/{investorId}")
    public List<AssetClassAllocationRule> getByInvestor(@PathVariable Long investorId) {
        return service.getRulesByInvestor(investorId);
    }

    @GetMapping("/active/{investorId}")
    public List<AssetClassAllocationRule> getActive(@PathVariable Long investorId) {
        return service.getActiveRules(investorId);
    }
}
