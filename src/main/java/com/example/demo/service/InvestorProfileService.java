package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.InvestorProfile;

public interface InvestorProfileService {
    InvestorProfile createInvestor(InvestorProfile investor);
    Optional<InvestorProfile> getInvestorById(Long id);
    Optional<InvestorProfile> findByInvestorId(String investorId);
    List<InvestorProfile> getAllInvestors();
}
