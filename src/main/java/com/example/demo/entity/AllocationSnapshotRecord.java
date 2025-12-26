package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllocationSnapshotRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long investorId;
    private LocalDateTime snapshotDate;
    private Double totalPortfolioValue;
    
    @Lob
    private String allocationJson;

    // Constructor without ID for creation
    public AllocationSnapshotRecord(Long investorId, LocalDateTime snapshotDate, Double totalPortfolioValue, String allocationJson) {
        this.investorId = investorId;
        this.snapshotDate = snapshotDate;
        this.totalPortfolioValue = totalPortfolioValue;
        this.allocationJson = allocationJson;
    }
}