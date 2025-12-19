package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;

public class AssetClassAllocationRule {
    private Long id;
    private Long investorId;
    private AssetClassType assetClass;
    private double targetPercentage;
    private boolean active = true;

    public AssetClassAllocationRule() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInvestorId() { return investorId; }
    public void setInvestorId(Long investorId) { this.investorId = investorId; }

    public AssetClassType getAssetClass() { return assetClass; }
    public void setAssetClass(AssetClassType assetClass) { this.assetClass = assetClass; }

    public double getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(double targetPercentage) {
        if(targetPercentage < 0 || targetPercentage > 100)
            throw new IllegalArgumentException("between 0 and 100");
        this.targetPercentage = targetPercentage;
    }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
