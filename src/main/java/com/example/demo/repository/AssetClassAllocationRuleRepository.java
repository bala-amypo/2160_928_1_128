package com.example.demo.repository;

import com.example.demo.entity.AssetClassAllocationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetClassAllocationRuleRepository extends JpaRepository<AssetClassAllocationRule, Long> {

    // Find rules by investor ID
    List<AssetClassAllocationRule> findByInvestorId(Long investorId);

    // Find active rules for an investor using HQL
    @Query("SELECT r FROM AssetClassAllocationRule r WHERE r.active = true AND r.investor.id = :investorId")
    List<AssetClassAllocationRule> findActiveRulesHql(@Param("investorId") Long investorId);
}
