package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.AllocationSnapshotService;

@RestController
@RequestMapping("/api/snapshots")
public class AllocationSnapshotController {

    @Autowired
    private AllocationSnapshotService service;

    @PostMapping("/compute/{investorId}")
    public AllocationSnapshotRecord compute(@PathVariable Long investorId) {
        return service.computeSnapshot(investorId);
    }

    @GetMapping("/{id}")
    public Optional<AllocationSnapshotRecord> getById(@PathVariable Long id) {
        return service.getSnapshotById(id);
    }

    @GetMapping("/investor/{investorId}")
    public List<AllocationSnapshotRecord> getByInvestor(@PathVariable Long investorId) {
        return service.getSnapshotsByInvestor(investorId);
    }
}
