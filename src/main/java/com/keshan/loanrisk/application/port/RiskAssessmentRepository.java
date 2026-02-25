package com.keshan.loanrisk.application.port;
import com.keshan.loanrisk.domain.model.*;

public interface RiskAssessmentRepository {
    RiskAssessment save(RiskAssessment assessment);
}
