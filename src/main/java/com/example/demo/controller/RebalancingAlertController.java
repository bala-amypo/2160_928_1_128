package com.example.demo.controller;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.service.RebalancingAlertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Rebalancing Alert API")
public class RebalancingAlertController {

    private final RebalancingAlertService service;

    public RebalancingAlertController(RebalancingAlertService service) {
        this.service = service;
    }

    @PostMapping
    public RebalancingAlertRecord createAlert(@RequestBody RebalancingAlertRecord alert) {
        return service.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public RebalancingAlertRecord resolveAlert(@PathVariable Long id) {
        return service.resolveAlert(id);
    }

    @GetMapping("/investor/{investorId}")
    public List<RebalancingAlertRecord> getAlertsByInvestor(@PathVariable Long investorId) {
        return service.getAlertsByInvestor(investorId);
    }

    @GetMapping("/{id}")
    public RebalancingAlertRecord getAlertById(@PathVariable Long id) {
        return service.getAlertById(id);
    }

    @GetMapping
    public List<RebalancingAlertRecord> getAllAlerts() {
        return service.getAllAlerts();
    }
}
