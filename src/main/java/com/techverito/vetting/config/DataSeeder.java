package com.techverito.vetting.config;

import com.techverito.vetting.domain.*;
import com.techverito.vetting.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProgramRepository programs;
    private final RegistrationRepository registrations;
    private final CompanyRepository companies;

    public DataSeeder(ProgramRepository programs, RegistrationRepository registrations, CompanyRepository companies) {
        this.programs = programs;
        this.registrations = registrations;
        this.companies = companies;
    }

    @Override
    public void run(String... args) {
        Program syndication = programs.save(new Program("Cloud Security Syndication", ProgramType.SYNDICATION));
        programs.save(new Program("Q4 Appointment Setting", ProgramType.APPOINTMENT));
        programs.save(new Program("Data Summit Webinar", ProgramType.WEBINAR));

        registrations.save(new Registration(syndication.getId(), "jane@acme.com", "Acme", "US", "CA", "US", "CA"));
        registrations.save(new Registration(syndication.getId(), "raj@globex.in", "Globex", "IN", "KA", "US", "NY"));

        companies.save(new Company("acme.com", "Acme", "501-1000", "$100M-$500M"));
        companies.save(new Company("globex.com", "Globex", "1001-2500", "$500M-$1B"));
    }
}
