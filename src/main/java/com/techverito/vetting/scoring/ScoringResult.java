package com.techverito.vetting.scoring;

import java.util.List;

/** Response of the AI scoring service for the registration's winning Target Spec. */
public record ScoringResult(int score,
                            boolean suppressionMatch,
                            boolean exclusionMatch,
                            List<String> matchedCriteria,
                            List<String> unmatchedCriteria) {}
