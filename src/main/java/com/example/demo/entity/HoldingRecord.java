package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;

public class HoldingRecord {
    private Long id;
    private Long investorId;
    private AssetClassType assetClass;
    private double currentValue;

    public HoldingRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInvestorId() { return investorId; }
    public void setInvestorId(Long investorId) { this.investorId = investorId; }

    public AssetClassType getAssetClass() { return assetClass; }
    public void setAssetClass(AssetClassType assetClass) { this.assetClass = assetClass; }

    public double getCurrentValue() { return currentValue; }
    public void setCurrentValue(double currentValue) {
        if(currentValue <= 0)
            throw new IllegalArgumentException("must be > 0");
        this.currentValue = currentValue;
    }
}
