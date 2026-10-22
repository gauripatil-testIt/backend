package com.techverito.vetting.web;

import com.techverito.vetting.domain.Registration;
import com.techverito.vetting.service.RegistrationService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Activity Tab export. The "Targeting Match Columns" story appends 5 columns here. */
@RestController
@RequestMapping("/api/programs/{programId}/registrations/export")
public class ExportController {

    private static final List<String> HEADERS = List.of(
            "ID", "Email", "Company", "Contact Country", "Contact State",
            "Company HQ Country", "Company HQ State", "Status", "AI Score", "Vetting Note");

    private final RegistrationService registrations;

    public ExportController(RegistrationService registrations) {
        this.registrations = registrations;
    }

    @GetMapping(produces = "text/csv")
    public ResponseEntity<String> export(@PathVariable Long programId) {
        StringBuilder csv = new StringBuilder(String.join(",", HEADERS)).append('\n');
        for (Registration r : registrations.list(programId)) {
            csv.append(List.of(
                    str(r.getId()), str(r.getEmail()), str(r.getCompany()), str(r.getContactCountry()),
                    str(r.getContactState()), str(r.getCompanyHqCountry()), str(r.getCompanyHqState()),
                    str(r.getStatus()), str(r.getAiScore()), str(r.getVettingNote()))
                    .stream().map(ExportController::escape).collect(Collectors.joining(","))).append('\n');
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=registrations-" + programId + ".csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv.toString());
    }

    private static String str(Object o) {
        return o == null ? "" : o.toString();
    }

    private static String escape(String s) {
        return s.contains(",") || s.contains("\"") || s.contains("\n") ? "\"" + s.replace("\"", "\"\"") + "\"" : s;
    }
}
