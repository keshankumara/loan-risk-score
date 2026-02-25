package com.keshan.loanrisk.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import com.keshan.loanrisk.application.port.LoanApplicationRepository;
import com.keshan.loanrisk.domain.model.LoanApplication;
import com.keshan.loanrisk.infrastructure.persistence.entity.LoanApplicationEntity;
import com.keshan.loanrisk.infrastructure.persistence.repository.JpaLoanRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LoanRepositoryImpl implements LoanApplicationRepository {

    private final JpaLoanRepository jpaRepository;

    @Override
    public LoanApplication save(LoanApplication loanApplication) {
        LoanApplicationEntity entity = new LoanApplicationEntity();
        entity.setApplicantName(loanApplication.getApplicantName());
        entity.setMonthlyIncome(loanApplication.getMonthlyIncome());
        entity.setExistingDebt(loanApplication.getExistingDebt());
        entity.setLatePayments(loanApplication.getLatePayments());
        entity.setEmploymentYears(loanApplication.getEmploymentYears());

        jpaRepository.save(entity);
        return loanApplication;
    }
}
