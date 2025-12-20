package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.AssetClassAllocationRule;

public interface AssetClassAllocationRuleRepository
        extends JpaRepository<AssetClassAllocationRule, Long> {

    List<AssetClassAllocationRule> findByInvestorld(Long investorld);

    @Query("""
        select r from AssetClassAllocationRule r
        where r.investorld = :investorld and r.active = true
    """)
    List<AssetClassAllocationRule> findActiveRulesHql(Long investorld);
}
