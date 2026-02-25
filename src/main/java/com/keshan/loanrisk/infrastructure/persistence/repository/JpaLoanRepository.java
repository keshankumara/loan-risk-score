package com.keshan.loanrisk.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.keshan.loanrisk.infrastructure.persistence.entity.LoanApplicationEntity;

public interface JpaLoanRepository extends JpaRepository<LoanApplicationEntity, Long> { }
