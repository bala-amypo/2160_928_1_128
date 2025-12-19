package com.example.demo.entity;

public class AllocationSnapshotRecord {
    private Long id;
    private Long investorId;
    private double totalPortfolioValue;
    private String allocationJson;

    public AllocationSnapshotRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInvestorId() { return investorId; }
    public void setInvestorId(Long investorId) { this.investorId = investorId; }

    public double getTotalPortfolioValue() { return totalPortfolioValue; }
    public void setTotalPortfolioValue(double totalPortfolioValue) {
        if(totalPortfolioValue <= 0)
            throw new IllegalArgumentException("must be > 0");
        this.totalPortfolioValue = totalPortfolioValue;
    }

    public String getAllocationJson() { return allocationJson; }
    public void setAllocationJson(String allocationJson) { this.allocationJson = allocationJson; }
}
