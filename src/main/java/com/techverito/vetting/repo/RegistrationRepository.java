package com.techverito.vetting.repo;

import com.techverito.vetting.domain.Registration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByProgramIdOrderByIdDesc(Long programId);
}
