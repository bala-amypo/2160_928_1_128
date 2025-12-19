package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.service.RebalancingAlertService;

@RestController
@RequestMapping("/api/alerts")
public class RebalancingAlertController {

    @Autowired
    private RebalancingAlertService service;

    @PostMapping
    public RebalancingAlertRecord create(@RequestBody RebalancingAlertRecord alert) {
        return service.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public RebalancingAlertRecord resolve(@PathVariable Long id) {
        return service.resolveAlert(id);
    }

    @GetMapping("/{id}")
    public Optional<RebalancingAlertRecord> getById(@PathVariable Long id) {
        return service.getAlertById(id);
    }

    @GetMapping("/investor/{investorId}")
    public List<RebalancingAlertRecord> getByInvestor(@PathVariable Long investorId) {
        return service.getAlertsByInvestor(investorId);
    }
}
