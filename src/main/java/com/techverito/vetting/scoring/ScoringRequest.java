package com.techverito.vetting.scoring;

import com.techverito.vetting.domain.Registration;
import java.util.List;

/** Simulation inputs let you drive the stub scorer from the UI / curl. */
public record ScoringRequest(Registration registration,
                             Integer simulatedScore,
                             Boolean simulatedSuppression,
                             Boolean simulatedExclusion,
                             List<String> simulatedUnmatched) {}
