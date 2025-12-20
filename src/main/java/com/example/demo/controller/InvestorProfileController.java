package com.example.demo.controller;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.InvestorProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investors")
@Tag(name = "Investor Profile API")
public class InvestorProfileController {

    private final InvestorProfileService service;

    public InvestorProfileController(InvestorProfileService service) {
        this.service = service;
    }

    @PostMapping
    public InvestorProfile createInvestor(@RequestBody InvestorProfile investor) {
        return service.createInvestor(investor);
    }

    @GetMapping("/{id}")
    public InvestorProfile getInvestorById(@PathVariable Long id) {
        return service.getInvestorById(id);
    }

    @GetMapping
    public List<InvestorProfile> getAllInvestors() {
        return service.getAllInvestors();
    }

    @PutMapping("/{id}/status")
    public InvestorProfile updateStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {
        return service.updateInvestorStatus(id, active);
    }

    @GetMapping("/lookup/{investorId}")
    public InvestorProfile findByInvestorId(@PathVariable String investorId) {
        return service.findByInvestorId(investorId);
    }
}
