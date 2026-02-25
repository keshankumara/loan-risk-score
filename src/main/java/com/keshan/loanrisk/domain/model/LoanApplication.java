package com.keshan.loanrisk.domain.model;

public class LoanApplication {

    private final String applicantName;
    private final double monthlyIncome;
    private final double existingDebt;
    private final int latePayments;
    private final int employmentYears;

    public LoanApplication(String applicantName,
                           double monthlyIncome,
                           double existingDebt,
                           int latePayments,
                           int employmentYears) {

        if (monthlyIncome <= 0) {
            throw new IllegalArgumentException("Income must be positive");
        }

        this.applicantName = applicantName;
        this.monthlyIncome = monthlyIncome;
        this.existingDebt = existingDebt;
        this.latePayments = latePayments;
        this.employmentYears = employmentYears;
    }

    public double calculateDebtToIncomeRatio() {
        return existingDebt / monthlyIncome;
    }

    public double getMonthlyIncome() { return monthlyIncome; }
    public int getLatePayments() { return latePayments; }
    public int getEmploymentYears() { return employmentYears; }
    public String getApplicantName() { return applicantName; }
    public double getExistingDebt() { return existingDebt; }

}
