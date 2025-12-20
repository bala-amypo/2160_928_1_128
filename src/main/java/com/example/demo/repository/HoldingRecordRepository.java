package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entityenums.AssetClassType;

public interface HoldingRecordRepository
        extends JpaRepository<HoldingRecord, Long> {

    List<HoldingRecord> findByInvestorld(Long investorld);

    Optional<HoldingRecord> findByInvestorldAndAssetClass(
            Long investorld,
            AssetClassType assetClass
    );
}
