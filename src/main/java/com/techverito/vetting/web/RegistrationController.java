package com.techverito.vetting.web;

import com.techverito.vetting.domain.Registration;
import com.techverito.vetting.service.RegistrationService;
import com.techverito.vetting.service.RegistrationService.ScoringRequestSimulation;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/programs/{programId}/registrations")
public class RegistrationController {

    private final RegistrationService registrations;

    public RegistrationController(RegistrationService registrations) {
        this.registrations = registrations;
    }

    @GetMapping
    public List<Registration> list(@PathVariable Long programId) {
        return registrations.list(programId);
    }

    /** Creates and scores a registration. The optional simulate* fields drive the stub scorer. */
    @PostMapping
    public Registration create(@PathVariable Long programId, @RequestBody CreateRegistration req) {
        Registration r = new Registration(programId, req.email(), req.company(), req.contactCountry(),
                req.contactState(), req.companyHqCountry(), req.companyHqState());
        return registrations.register(programId, r, req.simulation());
    }

    @PostMapping("/{id}/recalculate")
    public Registration recalculate(@PathVariable Long programId, @PathVariable Long id,
                                    @RequestBody(required = false) Simulation sim) {
        return registrations.recalculate(programId, id,
                sim == null ? ScoringRequestSimulation.none() : sim.toSimulation());
    }

    public record Simulation(Integer score, Boolean suppression, Boolean exclusion, List<String> unmatched) {
        ScoringRequestSimulation toSimulation() {
            return new ScoringRequestSimulation(score, suppression, exclusion, unmatched);
        }
    }

    public record CreateRegistration(String email, String company, String contactCountry, String contactState,
                                     String companyHqCountry, String companyHqState, Simulation simulate) {
        ScoringRequestSimulation simulation() {
            return simulate == null ? ScoringRequestSimulation.none() : simulate.toSimulation();
        }
    }
}
