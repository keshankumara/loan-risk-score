package com.keshan.loanrisk.application.usecase;
import com.keshan.loanrisk.domain.service.RiskCalculator;
import com.keshan.loanrisk.application.port.LoanApplicationRepository;
import com.keshan.loanrisk.application.port.RiskAssessmentRepository;
import com.keshan.loanrisk.application.mapper.LoanMapper;
import com.keshan.loanrisk.application.request.LoanRiskRequest;
import com.keshan.loanrisk.application.response.LoanRiskResponse;
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

    public LoanRiskResponse execute(LoanRiskRequest request) {

        // Convert request → Domain
        LoanApplication application = mapper.toDomain(request);

        // Calculate risk (Domain logic)
        RiskAssessment assessment = riskCalculator.calculate(application);

        // Persist through ports
        loanRepository.save(application);
        assessmentRepository.save(assessment);

        // Convert to Response
        return mapper.toResponse(assessment);
    }
}
