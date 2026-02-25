package com.keshan.loanrisk.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.keshan.loanrisk.infrastructure.persistence.entity.RiskAssessmentEntity;

public interface JpaRiskAssessmentRepository extends JpaRepository<RiskAssessmentEntity, Long> { }
