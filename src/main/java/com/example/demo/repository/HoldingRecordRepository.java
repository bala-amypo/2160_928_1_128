public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {
    List<HoldingRecord> findByInvestorId(Long investorId);
    List<HoldingRecord> findByValueGreaterThan(Double value);
    List<HoldingRecord> findByInvestorAndAssetClass(Long investorId, AssetClassType assetClass);
}
