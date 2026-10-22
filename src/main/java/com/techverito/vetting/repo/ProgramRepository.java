package com.techverito.vetting.repo;

import com.techverito.vetting.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {}
