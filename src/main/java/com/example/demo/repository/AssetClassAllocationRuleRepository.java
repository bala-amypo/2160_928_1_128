package com.example.demo.repository;

import com.example.demo.entity.AssetClassAllocationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetClassAllocationRuleRepository extends JpaRepository<AssetClassAllocationRule, Long> {

    // Existing method
    List<AssetClassAllocationRule> findByInvestorId(Long investorId);

    // Add this method to fix compilation
    @Query("SELECT r FROM AssetClassAllocationRule r WHERE r.active = true AND r.investor.id = :investorId")
    List<AssetClassAllocationRule> findActiveRules(@Param("investorId") Long investorId);
}
