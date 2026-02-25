package com.keshan.loanrisk.presentation.controller;

import org.springframework.web.bind.annotation.RestController;

import com.keshan.loanrisk.application.dto.LoanRiskRequestDTO;
import com.keshan.loanrisk.application.dto.LoanRiskResponseDTO;
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

        LoanRiskResponseDTO response = calculateRiskUseCase.execute(requestDTO);
        return ResponseEntity.ok(response);
    }
}
