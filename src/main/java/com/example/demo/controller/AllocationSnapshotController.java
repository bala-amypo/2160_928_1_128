package com.example.demo.controller;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.AllocationSnapshotService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/snapshots")
@Tag(name = "Allocation Snapshot API")
public class AllocationSnapshotController {

    private final AllocationSnapshotService service;

    public AllocationSnapshotController(AllocationSnapshotService service) {
        this.service = service;
    }

    @PostMapping("/compute/{investorId}")
    public AllocationSnapshotRecord computeSnapshot(@PathVariable Long investorId) {
        return service.computeSnapshot(investorId);
    }

    @GetMapping("/investor/{investorId}")
    public List<AllocationSnapshotRecord> getSnapshotsByInvestor(@PathVariable Long investorId) {
        return service.getSnapshotsByInvestor(investorId);
    }

    @GetMapping("/{id}")
    public AllocationSnapshotRecord getSnapshotById(@PathVariable Long id) {
        return service.getSnapshotById(id);
    }

    @GetMapping
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return service.getAllSnapshots();
    }
}
