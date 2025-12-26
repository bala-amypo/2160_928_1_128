package com.example.demo.controller;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.InvestorProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investors")
public class InvestorProfileController {
    
    private final InvestorProfileService investorProfileService;

    public InvestorProfileController(InvestorProfileService investorProfileService) {
        this.investorProfileService = investorProfileService;
    }

    // testServletLikeEndpointCreation (priority 1)
    @PostMapping
    public ResponseEntity<InvestorProfile> createInvestor(@RequestBody InvestorProfile investor) {
        InvestorProfile saved = investorProfileService.createInvestor(investor);
        return ResponseEntity.ok(saved);
    }

    // testServletLikeEndpointGetById (priority 2)
    @GetMapping("/{id}")
    public ResponseEntity<InvestorProfile> getInvestorById(@PathVariable Long id) {
        InvestorProfile investor = investorProfileService.getInvestorById(id);
        return ResponseEntity.ok(investor);
    }

    // testServletLikeEndpointListAll (priority 4)
    @GetMapping
    public ResponseEntity<List<InvestorProfile>> getAllInvestors() {
        List<InvestorProfile> investors = investorProfileService.getAllInvestors();
        return ResponseEntity.ok(investors);
    }

    // testServletLikeEndpointStatusUpdate (priority 5)
    @PutMapping("/{id}/status")
    public ResponseEntity<InvestorProfile> updateInvestorStatus(
            @PathVariable Long id, 
            @RequestParam boolean active) {
        InvestorProfile updated = investorProfileService.updateInvestorStatus(id, active);
        return ResponseEntity.ok(updated);
    }

    // testServletLikeEndpointLookupByInvestorId (priority 6)
    @GetMapping("/lookup/{investorId}")
    public ResponseEntity<Optional<InvestorProfile>> findByInvestorId(@PathVariable String investorId) {
        Optional<InvestorProfile> investor = investorProfileService.findByInvestorId(investorId);
        return ResponseEntity.ok(investor);
    }
}
