package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class AssetClassAllocationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long investorId;
    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;
    private Double targetPercentage;
    private Boolean active;

    public AssetClassAllocationRule(Long investorId, AssetClassType assetClass, Double targetPercentage, Boolean active) {
        if (targetPercentage < 0 || targetPercentage > 100) {
            throw new IllegalArgumentException("Target percentage must be between 0 and 100");
        }
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.targetPercentage = targetPercentage;
        this.active = active;
    }
    
    public void setTargetPercentage(Double targetPercentage) {
        if (targetPercentage < 0 || targetPercentage > 100) {
            throw new IllegalArgumentException("Target percentage must be between 0 and 100");
        }
        this.targetPercentage = targetPercentage;
    }
}