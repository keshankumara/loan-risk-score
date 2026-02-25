package com.keshan.loanrisk.infrastructure.persistence.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;

@Entity
@Table(name = "loan_application")
public class LoanApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantName;
    private double monthlyIncome;
    private double existingDebt;
    private int latePayments;
    private int employmentYears;

    // getters & setters
    public Long getId() { return id; }
    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }
    public double getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(double monthlyIncome) { this.monthlyIncome = monthlyIncome; }
    public double getExistingDebt() { return existingDebt; }
    public void setExistingDebt(double existingDebt) { this.existingDebt = existingDebt; }
    public int getLatePayments() { return latePayments; }
    public void setLatePayments(int latePayments) { this.latePayments = latePayments; }
    public int getEmploymentYears() { return employmentYears; }
    public void setEmploymentYears(int employmentYears) { this.employmentYears = employmentYears; }
    
}
