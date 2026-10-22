package com.techverito.vetting.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long programId;

    private String email;
    private String company;
    private String contactCountry;
    private String contactState;
    private String companyHqCountry;
    private String companyHqState;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status = RegistrationStatus.NEW;
    /** Null when the registration has never been scored. */
    private Integer aiScore;
    private String vettingNote;
    @Enumerated(EnumType.STRING)
    private VettingSource vettingSource;
    private Instant createdAt = Instant.now();

    protected Registration() {}

    public Registration(Long programId, String email, String company, String contactCountry, String contactState,
                        String companyHqCountry, String companyHqState) {
        this.programId = programId;
        this.email = email;
        this.company = company;
        this.contactCountry = contactCountry;
        this.contactState = contactState;
        this.companyHqCountry = companyHqCountry;
        this.companyHqState = companyHqState;
    }

    public Long getId() { return id; }
    public Long getProgramId() { return programId; }
    public String getEmail() { return email; }
    public String getCompany() { return company; }
    public String getContactCountry() { return contactCountry; }
    public String getContactState() { return contactState; }
    public String getCompanyHqCountry() { return companyHqCountry; }
    public String getCompanyHqState() { return companyHqState; }
    public RegistrationStatus getStatus() { return status; }
    public void setStatus(RegistrationStatus status) { this.status = status; }
    public Integer getAiScore() { return aiScore; }
    public void setAiScore(Integer aiScore) { this.aiScore = aiScore; }
    public String getVettingNote() { return vettingNote; }
    public void setVettingNote(String vettingNote) { this.vettingNote = vettingNote; }
    public VettingSource getVettingSource() { return vettingSource; }
    public void setVettingSource(VettingSource vettingSource) { this.vettingSource = vettingSource; }
    public Instant getCreatedAt() { return createdAt; }
}
