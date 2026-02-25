package com.keshan.loanrisk.domain.service;
import com.keshan.loanrisk.domain.model.*;

public class RiskCalculator {

    public RiskAssessment calculate(LoanApplication application) {

        int totalScore = 0;

        totalScore += calculateDTIScore(application.calculateDebtToIncomeRatio());
        totalScore += calculateLatePaymentScore(application.getLatePayments());
        totalScore += calculateEmploymentScore(application.getEmploymentYears());
        totalScore += calculateIncomeScore(application.getMonthlyIncome());

        RiskLevel level = determineRiskLevel(totalScore);
        Recommendation recommendation = determineRecommendation(level);

        return new RiskAssessment(totalScore, level, recommendation);
    }

    private int calculateDTIScore(double dti) {
        if (dti < 0.3) return 40;
        if (dti <= 0.5) return 25;
        return 10;
    }

    private int calculateLatePaymentScore(int latePayments) {
        if (latePayments == 0) return 25;
        if (latePayments == 1) return 15;
        return 5;
    }

    private int calculateEmploymentScore(int years) {
        if (years > 5) return 20;
        if (years >= 2) return 15;
        return 5;
    }

    private int calculateIncomeScore(double income) {
        if (income > 150000) return 15;
        if (income >= 80000) return 10;
        return 5;
    }

    private RiskLevel determineRiskLevel(int score) {
        if (score >= 75) return RiskLevel.LOW;
        if (score >= 50) return RiskLevel.MEDIUM;
        return RiskLevel.HIGH;
    }

    private Recommendation determineRecommendation(RiskLevel level) {
        switch (level) {
            case LOW: return Recommendation.APPROVE;
            case MEDIUM: return Recommendation.REVIEW;
            default: return Recommendation.REJECT;
        }
    }
}
