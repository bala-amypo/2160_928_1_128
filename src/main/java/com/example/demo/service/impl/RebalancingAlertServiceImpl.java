package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import com.example.demo.service.RebalancingAlertService;

@Service
public class RebalancingAlertServiceImpl implements RebalancingAlertService {

    @Autowired
    private RebalancingAlertRecordRepository repo;

    @Override
    public RebalancingAlertRecord createAlert(RebalancingAlertRecord alert) {
        return repo.save(alert);
    }

    @Override
    public RebalancingAlertRecord resolveAlert(Long id) {
        Optional<RebalancingAlertRecord> opt = repo.findById(id);
        if(opt.isPresent()) {
            RebalancingAlertRecord alert = opt.get();
            alert.setResolved(true);
            return repo.save(alert);
        }
        return null;
    }

    @Override
    public Optional<RebalancingAlertRecord> getAlertById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<RebalancingAlertRecord> getAlertsByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }
}
