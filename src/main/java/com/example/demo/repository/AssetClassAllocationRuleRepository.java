public interface AssetClassAllocationRuleRepository
        extends JpaRepository<AssetClassAllocationRule, Long> {

    List<AssetClassAllocationRule> findByInvestorId(Long investorId);

    @Query("SELECT r FROM AssetClassAllocationRule r WHERE r.investorId=:id AND r.active=true")
    List<AssetClassAllocationRule> findActiveRulesHql(@Param("id") Long investorId);
}
