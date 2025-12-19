public interface InvestorProfileRepository extends JpaRepository<InvestorProfile, Long> {
    Optional<InvestorProfile> findByInvestorId(String investorId);
}
