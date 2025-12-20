package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entityenums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {

    // REQUIRED by test suite
    List<HoldingRecord> findByInvestorId(Long investorId);

    // REQUIRED by test suite
    List<HoldingRecord> findByInvestorAndAssetClass(Long investorId, AssetClassType assetClass);
}
