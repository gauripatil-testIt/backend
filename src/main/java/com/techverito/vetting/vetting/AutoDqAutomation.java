package com.techverito.vetting.vetting;

import com.techverito.vetting.domain.*;
import com.techverito.vetting.scoring.ScoringResult;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/** Disqualify on score = 0%, suppression match or exclusion match. */
@Component
@Order(10)
public class AutoDqAutomation implements VettingAutomation {

    @Override
    public boolean apply(Program program, Registration registration, ScoringResult result) {
        if (!program.isAutoDq()) {
            return false;
        }
        String note = null;
        if (result.suppressionMatch()) {
            note = "Suppression list match";
        } else if (result.exclusionMatch()) {
            note = "Exclusion match";
        } else if (result.score() == 0) {
            note = "AI score 0%";
        }
        if (note == null) {
            return false;
        }
        registration.setStatus(RegistrationStatus.DISQUALIFIED);
        registration.setVettingNote(note);
        registration.setVettingSource(VettingSource.AUTO_DQ);
        return true;
    }
}
