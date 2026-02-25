package com.keshan.loanrisk.application.dto;

public class LoanRiskResponseDTO {

    private final int riskScore;
    private final String riskLevel;
    private final String recommendation;

    public LoanRiskResponseDTO(int riskScore,
                               String riskLevel,
                               String recommendation) {
        this.riskScore = riskScore;
        this.riskLevel = riskLevel;
        this.recommendation = recommendation;
    }

    public int getRiskScore() { return riskScore; }
    public String getRiskLevel() { return riskLevel; }
    public String getRecommendation() { return recommendation; }
    
}
