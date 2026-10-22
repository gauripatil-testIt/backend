package com.techverito.vetting.service;

import com.techverito.vetting.domain.Program;
import com.techverito.vetting.domain.Registration;
import com.techverito.vetting.repo.ProgramRepository;
import com.techverito.vetting.repo.RegistrationRepository;
import com.techverito.vetting.scoring.*;
import com.techverito.vetting.vetting.VettingService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegistrationService {

    private final ProgramRepository programs;
    private final RegistrationRepository registrations;
    private final AiScoringClient scoring;
    private final VettingService vetting;

    public RegistrationService(ProgramRepository programs, RegistrationRepository registrations,
                               AiScoringClient scoring, VettingService vetting) {
        this.programs = programs;
        this.registrations = registrations;
        this.scoring = scoring;
        this.vetting = vetting;
    }

    public List<Registration> list(Long programId) {
        return registrations.findByProgramIdOrderByIdDesc(programId);
    }

    @Transactional
    public Registration register(Long programId, Registration registration, ScoringRequestSimulation sim) {
        Program program = program(programId);
        Registration saved = registrations.save(registration);
        scoreAndVet(program, saved, sim);
        return saved;
    }

    @Transactional
    public Registration recalculate(Long programId, Long registrationId, ScoringRequestSimulation sim) {
        Program program = program(programId);
        Registration reg = registrations.findById(registrationId)
                .filter(r -> r.getProgramId().equals(programId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        scoreAndVet(program, reg, sim);
        return reg;
    }

    private void scoreAndVet(Program program, Registration reg, ScoringRequestSimulation sim) {
        ScoringResult result = scoring.score(new ScoringRequest(reg, sim.score(), sim.suppression(),
                sim.exclusion(), sim.unmatched()));
        vetting.applyAutomations(program, reg, result);
    }

    private Program program(Long id) {
        return programs.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public record ScoringRequestSimulation(Integer score, Boolean suppression, Boolean exclusion,
                                           List<String> unmatched) {
        public static ScoringRequestSimulation none() {
            return new ScoringRequestSimulation(null, null, null, null);
        }
    }
}
