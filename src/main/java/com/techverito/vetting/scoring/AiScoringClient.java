package com.techverito.vetting.scoring;

public interface AiScoringClient {
    ScoringResult score(ScoringRequest request);
}
