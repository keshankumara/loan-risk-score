package com.keshan.loanrisk.application.usecase;
import com.keshan.loanrisk.domain.service.RiskCalculator;
import com.keshan.loanrisk.application.port.LoanApplicationRepository;
import com.keshan.loanrisk.application.port.RiskAssessmentRepository;
import com.keshan.loanrisk.application.mapper.LoanMapper;
import com.keshan.loanrisk.application.dto.*;
import com.keshan.loanrisk.domain.model.*;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculateRiskUseCase {

    private final RiskCalculator riskCalculator;
    private final LoanApplicationRepository loanRepository;
    private final RiskAssessmentRepository assessmentRepository;
    private final LoanMapper mapper;

    public LoanRiskResponseDTO execute(LoanRiskRequestDTO requestDTO) {

        // 1️⃣ Convert DTO → Domain
        LoanApplication application = mapper.toDomain(requestDTO);

        // 2️⃣ Calculate risk (Domain logic)
        RiskAssessment assessment = riskCalculator.calculate(application);

        // 3️⃣ Persist through ports
        loanRepository.save(application);
        assessmentRepository.save(assessment);

        // 4️⃣ Convert to Response DTO
        return mapper.toResponse(assessment);
    }
}
