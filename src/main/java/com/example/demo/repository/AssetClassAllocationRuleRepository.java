package com.example.demo.repository;

import com.example.demo.entity.AssetClassAllocationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AssetClassAllocationRuleRepository extends JpaRepository<AssetClassAllocationRule, Long> {

    // Get all rules for a specific investor
    List<AssetClassAllocationRule> findByInvestorProfileId(Long investorId);

    // Get only active rules for a specific investor
    @Query("SELECT r FROM AssetClassAllocationRule r WHERE r.investorProfile.id = :investorId AND r.active = true")
    List<AssetClassAllocationRule> findActiveRules(@Param("investorId") Long investorId);
}
