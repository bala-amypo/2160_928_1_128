package com.example.demo.controller;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.HoldingRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/holdings")
public class HoldingRecordController {

    private final HoldingRecordService service;

    public HoldingRecordController(HoldingRecordService service) {
        this.service = service;
    }

    @PostMapping
    public HoldingRecord create(@RequestBody HoldingRecord record) {
        return service.recordHolding(record);
    }

    @GetMapping("/investor/{investorId}")
    public List<HoldingRecord> byInvestor(@PathVariable Long investorId) {
        return service.getHoldingsByInvestor(investorId);
    }

    @GetMapping("/{id}")
    public Optional<HoldingRecord> getById(@PathVariable Long id) {
        return service.getHoldingById(id);
    }

    @GetMapping
    public List<HoldingRecord> getAll() {
        return service.getAllHoldings();
    }
}
