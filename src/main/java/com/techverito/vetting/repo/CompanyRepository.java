package com.techverito.vetting.repo;

import com.techverito.vetting.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {}
