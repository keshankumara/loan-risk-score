package com.keshan.loanrisk.application.port;
import com.keshan.loanrisk.domain.model.*;

public interface LoanApplicationRepository {
    LoanApplication save(LoanApplication loanApplication);
}
