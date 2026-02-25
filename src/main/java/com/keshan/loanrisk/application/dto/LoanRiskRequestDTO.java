package com.keshan.loanrisk.application.dto;
import jakarta.validation.constraints.*;

public class LoanRiskRequestDTO {
    @NotBlank(message = "Applicant name is required")
    private String applicantName;

    @Positive(message = "Monthly income must be positive")
    private double monthlyIncome;

    @PositiveOrZero(message = "Existing debt cannot be negative")
    private double existingDebt;

    @Min(value = 0, message = "Late payments cannot be negative")
    private int latePayments;

    @Min(value = 0, message = "Employment years cannot be negative")
    private int employmentYears;

    /* 
    private String applicantName;
    private double monthlyIncome;
    private double existingDebt;
    private int latePayments;
    private int employmentYears; */


    public LoanRiskRequestDTO() {}

    public LoanRiskRequestDTO(String applicantName,
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
