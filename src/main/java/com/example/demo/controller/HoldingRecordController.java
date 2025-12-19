package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.HoldingRecordService;

@RestController
@RequestMapping("/api/holdings")
public class HoldingRecordController {

    @Autowired
    private HoldingRecordService service;

    @PostMapping
    public HoldingRecord save(@RequestBody HoldingRecord holding) {
        return service.saveHolding(holding);
    }

    @GetMapping("/{id}")
    public Optional<HoldingRecord> getById(@PathVariable Long id) {
        return service.getHoldingById(id);
    }

    @GetMapping("/investor/{investorId}")
    public List<HoldingRecord> getByInvestor(@PathVariable Long investorId) {
        return service.getHoldingsByInvestor(investorId);
    }
}
