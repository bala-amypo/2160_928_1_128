package com.example.demo.controller;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.AllocationRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocation-rules")
@Tag(name = "Allocation Rule API")
public class AllocationRuleController {

    private final AllocationRuleService service;

    public AllocationRuleController(AllocationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public AssetClassAllocationRule createRule(@RequestBody AssetClassAllocationRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/{id}")
    public AssetClassAllocationRule updateRule(
            @PathVariable Long id,
            @RequestBody AssetClassAllocationRule rule) {
        return service.updateRule(id, rule);
    }

    @GetMapping("/investor/{investorId}")
    public List<AssetClassAllocationRule> getRulesByInvestor(@PathVariable Long investorId) {
        return service.getRulesByInvestor(investorId);
    }

    @GetMapping("/{id}")
    public AssetClassAllocationRule getRuleById(@PathVariable Long id) {
        return service.getRuleById(id);
    }

    @GetMapping
    public List<AssetClassAllocationRule> getAllRules() {
        return service.getAllRules();
    }
}
