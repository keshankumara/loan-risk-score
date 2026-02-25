package com.keshan.loanrisk.application.mapper;
import com.keshan.loanrisk.domain.model.*;
import com.keshan.loanrisk.application.dto.*;

public class LoanMapper {

    public LoanApplication toDomain(LoanRiskRequestDTO dto) {

        return new LoanApplication(
                dto.getApplicantName(),
                dto.getMonthlyIncome(),
                dto.getExistingDebt(),
                dto.getLatePayments(),
                dto.getEmploymentYears()
        );
    }

    public LoanRiskResponseDTO toResponse(RiskAssessment assessment) {

        return new LoanRiskResponseDTO(
                assessment.getScore(),
                assessment.getRiskLevel().name(),
                assessment.getRecommendation().name()
        );
    }
}
