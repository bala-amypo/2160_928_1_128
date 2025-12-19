package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.InvestorProfile;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfileService;

@Service
public class InvestorProfileServiceImpl implements InvestorProfileService {

    @Autowired
    private InvestorProfileRepository repo;

    @Override
    public InvestorProfile createInvestor(InvestorProfile investor) {
        return repo.save(investor);
    }

    @Override
    public Optional<InvestorProfile> getInvestorById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Optional<InvestorProfile> findByInvestorId(String investorId) {
        return repo.findByInvestorId(investorId);
    }

    @Override
    public List<InvestorProfile> getAllInvestors() {
        return repo.findAll();
    }
}
