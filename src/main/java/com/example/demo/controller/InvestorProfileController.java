package com.example.demo.controller;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.impl.InvestorProfileServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/investors")
public class InvestorProfileController {
    private final InvestorProfileServiceImpl service;

    public InvestorProfileController(InvestorProfileServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InvestorProfile> create(@RequestBody InvestorProfile investor) {
        return ResponseEntity.ok(service.createInvestor(investor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestorProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getInvestorById(id));
    }
    
    // Additional endpoints as required...
}