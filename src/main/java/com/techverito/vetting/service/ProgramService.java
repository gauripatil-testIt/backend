package com.techverito.vetting.service;

import com.techverito.vetting.domain.Program;
import com.techverito.vetting.repo.ProgramRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProgramService {

    private final ProgramRepository programs;

    public ProgramService(ProgramRepository programs) {
        this.programs = programs;
    }

    public List<Program> list() {
        return programs.findAll();
    }

    public Program get(Long id) {
        return programs.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /** Partial update: only non-null fields are applied. */
    @Transactional
    public Program updateVettingSettings(Long id, VettingSettingsUpdate u) {
        Program p = get(id);
        if (u.vettingAutomationEnabled() != null) p.setVettingAutomationEnabled(u.vettingAutomationEnabled());
        if (u.autoApprove100() != null) p.setAutoApprove100(u.autoApprove100());
        if (u.autoDq() != null) p.setAutoDq(u.autoDq());
        return p;
    }

    public record VettingSettingsUpdate(Boolean vettingAutomationEnabled, Boolean autoApprove100, Boolean autoDq) {}
}
