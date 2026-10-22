package com.techverito.vetting.vetting;

import com.techverito.vetting.domain.Program;
import com.techverito.vetting.domain.Registration;
import com.techverito.vetting.scoring.ScoringResult;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VettingService {

    private final List<VettingAutomation> automations;

    public VettingService(List<VettingAutomation> automations) {
        this.automations = automations;
    }

    /** Called after every AI scoring event (new registration and recalculation). */
    public void applyAutomations(Program program, Registration registration, ScoringResult result) {
        registration.setAiScore(result.score());
        if (!program.isVettingAutomationEnabled()) {
            return;
        }
        for (VettingAutomation automation : automations) {
            if (automation.apply(program, registration, result)) {
                return;
            }
        }
    }
}
