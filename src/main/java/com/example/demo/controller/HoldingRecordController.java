package com.example.demo.controller;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.HoldingRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holdings")
@Tag(name = "Holding Record API")
public class HoldingRecordController {

    private final HoldingRecordService service;

    public HoldingRecordController(HoldingRecordService service) {
        this.service = service;
    }

    @PostMapping
    public HoldingRecord recordHolding(@RequestBody HoldingRecord holding) {
        return service.recordHolding(holding);
    }

    @GetMapping("/investor/{investorId}")
    public List<HoldingRecord> getHoldingsByInvestor(@PathVariable Long investorId) {
        return service.getHoldingsByInvestor(investorId);
    }

    @GetMapping("/{id}")
    public HoldingRecord getHoldingById(@PathVariable Long id) {
        return service.getHoldingById(id);
    }

    @GetMapping
    public List<HoldingRecord> getAllHoldings() {
        return service.getAllHoldings();
    }
}
