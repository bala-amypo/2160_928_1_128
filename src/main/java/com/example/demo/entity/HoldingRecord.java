package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class HoldingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long investorId;
    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;
    private Double currentValue;
    private LocalDateTime snapshotDate;

    public HoldingRecord(Long investorId, AssetClassType assetClass, Double currentValue, LocalDateTime snapshotDate) {
        // Validation removed here to allow Test instantiation. Moved to Service.
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.currentValue = currentValue;
        this.snapshotDate = snapshotDate;
    }
}