package com.example.demo.service;

import com.example.demo.entity.InvestorProfile;

import java.util.List;
import java.util.Optional;

public interface InvestorProfileService {
    /**
     * Creates a new investor profile
     * Covers: testServletLikeEndpointCreation (priority 1)
     */
    InvestorProfile createInvestor(InvestorProfile investor);

    /**
     * Retrieves investor by ID
     * Covers: testServletLikeEndpointGetById (priority 2), testServletLikeEndpointGetByIdNotFound (priority 3)
     */
    InvestorProfile getInvestorById(Long id);

    /**
     * Retrieves all investors
     * Covers: testServletLikeEndpointListAll (priority 4)
     */
    List<InvestorProfile> getAllInvestors();

    /**
     * Updates investor status (active/inactive)
     * Covers: testServletLikeEndpointStatusUpdate (priority 5)
     */
    InvestorProfile updateInvestorStatus(Long id, boolean active);

    /**
     * Finds investor by unique investorId
     * Covers: testServletLikeEndpointLookupByInvestorId (priority 6), testServletLikeEndpointLookupByInvestorIdNotFound (priority 7)
     */
    Optional<InvestorProfile> findByInvestorId(String investorId);
}
