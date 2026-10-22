package com.techverito.vetting.domain;

import jakarta.persistence.*;

/** Gold-layer company record. Placeholder for the ZoomInfo ingestion story. */
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domain;
    private String name;
    private String employeeRange;
    private String revenueRange;
    // Added by the ZoomInfo ingestion story:
    private Integer employeeSize;
    private Long annualRevenue;

    protected Company() {}

    public Company(String domain, String name, String employeeRange, String revenueRange) {
        this.domain = domain;
        this.name = name;
        this.employeeRange = employeeRange;
        this.revenueRange = revenueRange;
    }

    public Long getId() { return id; }
    public String getDomain() { return domain; }
    public String getName() { return name; }
    public String getEmployeeRange() { return employeeRange; }
    public void setEmployeeRange(String v) { this.employeeRange = v; }
    public String getRevenueRange() { return revenueRange; }
    public void setRevenueRange(String v) { this.revenueRange = v; }
    public Integer getEmployeeSize() { return employeeSize; }
    public void setEmployeeSize(Integer v) { this.employeeSize = v; }
    public Long getAnnualRevenue() { return annualRevenue; }
    public void setAnnualRevenue(Long v) { this.annualRevenue = v; }
}
