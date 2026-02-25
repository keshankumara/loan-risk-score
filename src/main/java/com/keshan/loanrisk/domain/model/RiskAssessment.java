package com.keshan.loanrisk.domain.model;

public class RiskAssessment {

    private final int score;
    private final RiskLevel riskLevel;
    private final Recommendation recommendation;

    public RiskAssessment(int score,
                          RiskLevel riskLevel,
                          Recommendation recommendation) {
        this.score = score;
        this.riskLevel = riskLevel;
        this.recommendation = recommendation;
    }

    public int getScore() { return score; }
    public RiskLevel getRiskLevel() { return riskLevel; }
    public Recommendation getRecommendation() { return recommendation; }
}
