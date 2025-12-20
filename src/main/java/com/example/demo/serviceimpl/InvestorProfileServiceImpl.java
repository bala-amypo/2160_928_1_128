package com.example.demo.serviceimpl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfileService;
import org.springframework.stereotype.Service;

@Service
public class InvestorProfileServiceImpl implements InvestorProfileService {

    private final InvestorProfileRepository repository;

    public InvestorProfileServiceImpl(InvestorProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public InvestorProfile getByInvestorId(String investorId) {
        return repository.findByInvestorId(investorId)
                .orElseThrow(() ->
                        new RuntimeException("InvestorProfile not found for ID: " + investorId)
                );
    }
}
