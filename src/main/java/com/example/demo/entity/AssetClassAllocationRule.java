package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "asset_class_allocation_rules")
public class AssetClassAllocationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double targetAllocation;

    @Column(nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private UserAccount investor;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getTargetAllocation() { return targetAllocation; }
    public void setTargetAllocation(double targetAllocation) { this.targetAllocation = targetAllocation; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public UserAccount getInvestor() { return investor; }
    public void setInvestor(UserAccount investor) { this.investor = investor; }
}
