package com.keshan.loanrisk.application.response;

/**
 * Application layer response object (no HTTP concerns)
 * Used as output from use cases at the application boundary
 */
public class LoanRiskResponse {
    private final int riskScore;
    private final String riskLevel;
    private final String recommendation;

    public LoanRiskResponse(int riskScore,
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
