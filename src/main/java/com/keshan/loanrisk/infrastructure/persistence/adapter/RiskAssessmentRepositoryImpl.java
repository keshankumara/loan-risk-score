package com.keshan.loanrisk.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import com.keshan.loanrisk.application.port.RiskAssessmentRepository;
import com.keshan.loanrisk.domain.model.RiskAssessment;
import com.keshan.loanrisk.infrastructure.persistence.entity.RiskAssessmentEntity;
import com.keshan.loanrisk.infrastructure.persistence.repository.JpaRiskAssessmentRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RiskAssessmentRepositoryImpl implements RiskAssessmentRepository {

    private final JpaRiskAssessmentRepository jpaRepository;

    @Override
    public RiskAssessment save(RiskAssessment assessment) {
        RiskAssessmentEntity entity = new RiskAssessmentEntity();
        entity.setScore(assessment.getScore());
        entity.setRiskLevel(assessment.getRiskLevel().name());
        entity.setRecommendation(assessment.getRecommendation().name());

        jpaRepository.save(entity);
        return assessment;
    }
}
