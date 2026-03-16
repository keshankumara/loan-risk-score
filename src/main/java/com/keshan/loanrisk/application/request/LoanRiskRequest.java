package com.keshan.loanrisk.application.request;

/**
 * Application layer request object (no HTTP concerns)
 * Used as input to use cases at the application boundary
 */
public class LoanRiskRequest {
    private final String applicantName;
    private final double monthlyIncome;
    private final double existingDebt;
    private final int latePayments;
    private final int employmentYears;

    public LoanRiskRequest(String applicantName,
                          double monthlyIncome,
                          double existingDebt,
                          int latePayments,
                          int employmentYears) {
        this.applicantName = applicantName;
        this.monthlyIncome = monthlyIncome;
        this.existingDebt = existingDebt;
        this.latePayments = latePayments;
        this.employmentYears = employmentYears;
    }

    public String getApplicantName() { return applicantName; }
    public double getMonthlyIncome() { return monthlyIncome; }
    public double getExistingDebt() { return existingDebt; }
    public int getLatePayments() { return latePayments; }
    public int getEmploymentYears() { return employmentYears; }
}
