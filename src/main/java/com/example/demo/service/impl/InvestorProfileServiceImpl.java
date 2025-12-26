package com.example.demo.service.impl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InvestorProfileServiceImpl implements InvestorProfileService {

    private final InvestorProfileRepository investorProfileRepository;

    public InvestorProfileServiceImpl(InvestorProfileRepository investorProfileRepository) {
        this.investorProfileRepository = investorProfileRepository;
    }

    @Override
    public InvestorProfile createInvestor(InvestorProfile investor) {
        validateInvestor(investor);
        return investorProfileRepository.save(investor);
    }

    @Override
    public InvestorProfile getInvestorById(Long id) {
        return investorProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investor", "id", id));
    }

    @Override
    public List<InvestorProfile> getAllInvestors() {
        return investorProfileRepository.findAll();
    }

    @Override
    public InvestorProfile updateInvestorStatus(Long id, boolean active) {
        InvestorProfile investor = getInvestorById(id);
        investor.setActive(active);
        return investorProfileRepository.save(investor);
    }

    @Override
    public Optional<InvestorProfile> findByInvestorId(String investorId) {
        return investorProfileRepository.findByInvestorId(investorId);
    }

    private void validateInvestor(InvestorProfile investor) {
        if (investor.getInvestorId() == null || investor.getInvestorId().trim().isEmpty()) {
            throw new IllegalArgumentException("Investor ID cannot be empty");
        }
        if (investor.getTargetPercentage() != null && (investor.getTargetPercentage() < 0 || investor.getTargetPercentage() > 100)) {
            throw new IllegalArgumentException("Target percentage must be between 0 and 100");
        }
    }
}
