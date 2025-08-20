package com.bsf.GymMembership.infrastructure.bean;

import com.bsf.GymMembership.core.gateway.PlanGateway;
import com.bsf.GymMembership.core.usecases.CreatePlanCase;
import com.bsf.GymMembership.core.usecases.CreatePlanCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public CreatePlanCase createPlanCase(PlanGateway planGateway){
        return new CreatePlanCaseImpl(planGateway);
    }
}
