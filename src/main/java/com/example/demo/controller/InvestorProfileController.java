package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.InvestorProfileService;

@RestController
@RequestMapping("/api/investors")
public class InvestorProfileController {

    @Autowired
    private InvestorProfileService service;

    @PostMapping
    public InvestorProfile createInvestor(@RequestBody InvestorProfile investor) {
        return service.createInvestor(investor);
    }

    @GetMapping("/{id}")
    public Optional<InvestorProfile> getInvestor(@PathVariable Long id) {
        return service.getInvestorById(id);
    }

    @GetMapping
    public List<InvestorProfile> getAllInvestors() {
        return service.getAllInvestors();
    }

    @GetMapping("/lookup/{investorId}")
    public Optional<InvestorProfile> getByInvestorId(@PathVariable String investorId) {
        return service.findByInvestorId(investorId);
    }
}
