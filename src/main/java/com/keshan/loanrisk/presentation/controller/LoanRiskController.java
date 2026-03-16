package com.keshan.loanrisk.presentation.controller;

import org.springframework.web.bind.annotation.RestController;

import com.keshan.loanrisk.presentation.dto.LoanRiskRequestDTO;
import com.keshan.loanrisk.presentation.dto.LoanRiskResponseDTO;
import com.keshan.loanrisk.application.request.LoanRiskRequest;
import com.keshan.loanrisk.application.usecase.CalculateRiskUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan-risk")
@RequiredArgsConstructor
public class LoanRiskController {

    private final CalculateRiskUseCase calculateRiskUseCase;

    @PostMapping("/calculate")
    public ResponseEntity<LoanRiskResponseDTO> calculateRisk(
            @RequestBody @Valid LoanRiskRequestDTO requestDTO) {

        // Map presentation DTO to application request object
        LoanRiskRequest appRequest = new LoanRiskRequest(
                requestDTO.getApplicantName(),
                requestDTO.getMonthlyIncome(),
                requestDTO.getExistingDebt(),
                requestDTO.getLatePayments(),
                requestDTO.getEmploymentYears()
        );

        // Call use case
        var appResponse = calculateRiskUseCase.execute(appRequest);

        // Map application response to presentation DTO
        LoanRiskResponseDTO responseDTO = new LoanRiskResponseDTO(
                appResponse.getRiskScore(),
                appResponse.getRiskLevel(),
                appResponse.getRecommendation()
        );

        return ResponseEntity.ok(responseDTO);
    }
}
