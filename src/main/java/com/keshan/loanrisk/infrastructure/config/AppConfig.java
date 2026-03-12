package com.keshan.loanrisk.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.keshan.loanrisk.domain.service.RiskCalculator;
import com.keshan.loanrisk.application.mapper.LoanMapper;

@Configuration
public class AppConfig {

    @Bean
    public RiskCalculator riskCalculator() {
        return new RiskCalculator();
    }

    @Bean
    public LoanMapper loanMapper() {
        return new LoanMapper();
    }
}
