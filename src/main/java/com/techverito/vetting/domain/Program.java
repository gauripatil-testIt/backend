package com.techverito.vetting.domain;

import jakarta.persistence.*;

@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ProgramType type;

    // Vetting Automation settings (Activity Tab)
    private boolean vettingAutomationEnabled = true;
    private boolean autoApprove100 = true;
    private boolean autoDq = true;

    protected Program() {}

    public Program(String name, ProgramType type) {
        this.name = name;
        this.type = type;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public ProgramType getType() { return type; }
    public boolean isVettingAutomationEnabled() { return vettingAutomationEnabled; }
    public void setVettingAutomationEnabled(boolean v) { this.vettingAutomationEnabled = v; }
    public boolean isAutoApprove100() { return autoApprove100; }
    public void setAutoApprove100(boolean v) { this.autoApprove100 = v; }
    public boolean isAutoDq() { return autoDq; }
    public void setAutoDq(boolean v) { this.autoDq = v; }
}
