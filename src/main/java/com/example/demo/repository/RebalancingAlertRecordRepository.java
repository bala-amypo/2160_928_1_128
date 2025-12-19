public interface RebalancingAlertRecordRepository
        extends JpaRepository<RebalancingAlertRecord, Long> {

    List<RebalancingAlertRecord> findByInvestorId(Long investorId);
    List<RebalancingAlertRecord> findByResolvedFalse();
}
