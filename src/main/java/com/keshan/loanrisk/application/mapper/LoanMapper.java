package com.keshan.loanrisk.application.mapper;
import com.keshan.loanrisk.domain.model.*;
import com.keshan.loanrisk.application.request.LoanRiskRequest;
import com.keshan.loanrisk.application.response.LoanRiskResponse;

public class LoanMapper {

    public LoanApplication toDomain(LoanRiskRequest request) {

        return new LoanApplication(
                request.getApplicantName(),
                request.getMonthlyIncome(),
                request.getExistingDebt(),
                request.getLatePayments(),
                request.getEmploymentYears()
        );
    }

    public LoanRiskResponse toResponse(RiskAssessment assessment) {

        return new LoanRiskResponse(
                assessment.getScore(),
                assessment.getRiskLevel().name(),
                assessment.getRecommendation().name()
        );
    }
}
