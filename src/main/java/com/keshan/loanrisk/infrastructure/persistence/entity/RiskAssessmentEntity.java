package com.keshan.loanrisk.infrastructure.persistence.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "risk_assessment")
public class RiskAssessmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;
    private String riskLevel;
    private String recommendation;

    @OneToOne
    @JoinColumn(name = "loan_id")
    private LoanApplicationEntity loan;

    // getters & setters
    public Long getId() { return id; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
    public LoanApplicationEntity getLoan() { return loan; }
    public void setLoan(LoanApplicationEntity loan) { this.loan = loan; }
}