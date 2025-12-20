package com.example.demo.serviceimpl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorProfileServiceImpl implements InvestorProfileService {

    private final InvestorProfileRepository repository;

    public InvestorProfileServiceImpl(InvestorProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public InvestorProfile createInvestor(InvestorProfile profile) {
        return repository.save(profile);
    }

    @Override
    public InvestorProfile getInvestorById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Investor not found with id: " + id));
    }

    @Override
    public List<InvestorProfile> getAllInvestors() {
        return repository.findAll();
    }

    @Override
    public InvestorProfile getByInvestorId(String investorId) {
        return repository.findByInvestorId(investorId)
                .orElseThrow(() ->
                        new RuntimeException("Investor not found: " + investorId));
    }

    @Override
    public InvestorProfile updateInvestorStatus(Long id, Boolean active) {
        InvestorProfile profile = getInvestorById(id);
        profile.setActive(active);
        return repository.save(profile);
    }
}
