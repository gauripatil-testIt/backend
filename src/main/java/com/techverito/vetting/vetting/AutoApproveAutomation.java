package com.techverito.vetting.vetting;

import com.techverito.vetting.domain.*;
import com.techverito.vetting.scoring.ScoringResult;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/** Approve on a 100% score. */
@Component
@Order(20)
public class AutoApproveAutomation implements VettingAutomation {

    @Override
    public boolean apply(Program program, Registration registration, ScoringResult result) {
        if (!program.isAutoApprove100() || result.score() != 100) {
            return false;
        }
        registration.setStatus(RegistrationStatus.APPROVED);
        registration.setVettingSource(VettingSource.AUTO_APPROVE);
        return true;
    }
}
