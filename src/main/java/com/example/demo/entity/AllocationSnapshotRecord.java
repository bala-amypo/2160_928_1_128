package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "allocation_snapshots")
public class AllocationSnapshotRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "investor_id", nullable = false)
    private Long investorId;

    @Column(nullable = false)
    private LocalDateTime snapshotDate;

    @Column(name = "total_portfolio_value", nullable = false)
    private Double totalPortfolioValue;

    @Column(columnDefinition = "TEXT")
    private String allocationData;

    public AllocationSnapshotRecord() {}

    public AllocationSnapshotRecord(Long investorId, LocalDateTime snapshotDate, Double totalPortfolioValue, String allocationData) {
        this.investorId = investorId;
        this.snapshotDate = snapshotDate != null ? snapshotDate : LocalDateTime.now();
        this.totalPortfolioValue = totalPortfolioValue;
        this.allocationData = allocationData;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getInvestorId() { return investorId; }
    public void setInvestorId(Long investorId) { this.investorId = investorId; }
    public LocalDateTime getSnapshotDate() { return snapshotDate; }
    public void setSnapshotDate(LocalDateTime snapshotDate) { this.snapshotDate = snapshotDate; }
    public Double getTotalPortfolioValue() { return totalPortfolioValue; }
    public void setTotalPortfolioValue(Double totalPortfolioValue) { this.totalPortfolioValue = totalPortfolioValue; }
    public String getAllocationData() { return allocationData; }
    public void setAllocationData(String allocationData) { this.allocationData = allocationData; }
}
