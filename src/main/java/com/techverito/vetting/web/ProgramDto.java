package com.techverito.vetting.web;

import com.techverito.vetting.domain.Program;
import com.techverito.vetting.domain.ProgramType;

public record ProgramDto(Long id, String name, ProgramType type, VettingSettings vettingSettings) {

    public record VettingSettings(boolean vettingAutomationEnabled, boolean autoApprove100, boolean autoDq) {}

    public static ProgramDto from(Program p) {
        return new ProgramDto(p.getId(), p.getName(), p.getType(),
                new VettingSettings(p.isVettingAutomationEnabled(), p.isAutoApprove100(), p.isAutoDq()));
    }
}
