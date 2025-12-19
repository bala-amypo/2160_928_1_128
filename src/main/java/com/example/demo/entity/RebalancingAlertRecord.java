package com.example.demo.entity;

import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.entity.enums.AssetClassType;

public class RebalancingAlertRecord {
    private Long id;
    private Long investorId;
    private AssetClassType assetClass;
    private double currentPercentage;
    private double targetPercentage;
    private AlertSeverity severity;
    private boolean resolved = false;

    public RebalancingAlertRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInvestorId() { return investorId; }
    public void setInvestorId(Long investorId) { this.investorId = investorId; }

    public AssetClassType getAssetClass() { return assetClass; }
    public void setAssetClass(AssetClassType assetClass) { this.assetClass = assetClass; }

    public double getCurrentPercentage() { return currentPercentage; }
    public void setCurrentPercentage(double currentPercentage) { this.currentPercentage = currentPercentage; }

    public double getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(double targetPercentage) { this.targetPercentage = targetPercentage; }

    public AlertSeverity getSeverity() { return severity; }
    public void setSeverity(AlertSeverity severity) { this.severity = severity; }

    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
}
