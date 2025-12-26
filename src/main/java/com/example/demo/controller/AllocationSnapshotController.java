package com.example.demo.controller;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.AllocationSnapshotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/snapshots")
public class AllocationSnapshotController {
    
    private final AllocationSnapshotService allocationSnapshotService;

    public AllocationSnapshotController(AllocationSnapshotService allocationSnapshotService) {
        this.allocationSnapshotService = allocationSnapshotService;
    }

    // testSnapshotComputationWithAlerts (priority 67)
    @PostMapping("/compute/{investorId}")
    public ResponseEntity<AllocationSnapshotRecord> computeSnapshot(@PathVariable Long investorId) {
        AllocationSnapshotRecord snapshot = allocationSnapshotService.computeSnapshot(investorId);
        return ResponseEntity.ok(snapshot);
    }

    // testGetSnapshotByIdNotFound (priority 69)
    @GetMapping("/{id}")
    public ResponseEntity<AllocationSnapshotRecord> getSnapshotById(@PathVariable Long id) {
        AllocationSnapshotRecord snapshot = allocationSnapshotService.getSnapshotById(id);
        return ResponseEntity.ok(snapshot);
    }

    // testGetAllSnapshotsEmpty (priority 70)
    @GetMapping
    public ResponseEntity<List<AllocationSnapshotRecord>> getAllSnapshots() {
        List<AllocationSnapshotRecord> snapshots = allocationSnapshotService.getAllSnapshots();
        return ResponseEntity.ok(snapshots);
    }
}
