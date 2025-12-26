package com.example.demo.controller;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.impl.AllocationSnapshotServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/snapshots")
public class AllocationSnapshotController {
    private final AllocationSnapshotServiceImpl service;

    public AllocationSnapshotController(AllocationSnapshotServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/compute/{investorId}")
    public ResponseEntity<AllocationSnapshotRecord> compute(@PathVariable Long investorId) {
        return ResponseEntity.ok(service.computeSnapshot(investorId));
    }

    @GetMapping
    public ResponseEntity<List<AllocationSnapshotRecord>> getAll() {
        return ResponseEntity.ok(service.getAllSnapshots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllocationSnapshotRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSnapshotById(id));
    }
}