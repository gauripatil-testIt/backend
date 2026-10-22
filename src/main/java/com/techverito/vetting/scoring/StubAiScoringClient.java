package com.techverito.vetting.scoring;

import java.util.List;
import org.springframework.stereotype.Component;

/** Stand-in for the real AI scoring service. Replace with an HTTP client later. */
@Component
public class StubAiScoringClient implements AiScoringClient {

    @Override
    public ScoringResult score(ScoringRequest r) {
        int score = r.simulatedScore() != null ? r.simulatedScore() : 60;
        List<String> unmatched = r.simulatedUnmatched() != null ? r.simulatedUnmatched() : List.of();
        if (score == 100) {
            unmatched = List.of();
        }
        return new ScoringResult(
                score,
                Boolean.TRUE.equals(r.simulatedSuppression()),
                Boolean.TRUE.equals(r.simulatedExclusion()),
                List.of("Job Title", "Industry"),
                unmatched);
    }
}
