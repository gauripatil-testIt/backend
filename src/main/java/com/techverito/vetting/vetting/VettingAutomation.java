package com.techverito.vetting.vetting;

import com.techverito.vetting.domain.Program;
import com.techverito.vetting.domain.Registration;
import com.techverito.vetting.scoring.ScoringResult;

/**
 * One auto-vetting rule. Automations run in @Order after each scoring event; the first one that
 * changes the status wins, and the rest are skipped for that event.
 */
public interface VettingAutomation {
    /** @return true if this automation changed the registration's status. */
    boolean apply(Program program, Registration registration, ScoringResult result);
}
