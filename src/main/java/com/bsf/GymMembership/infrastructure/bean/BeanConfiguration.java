package com.bsf.GymMembership.infrastructure.bean;

import com.bsf.GymMembership.core.gateway.PlanGateway;
import com.bsf.GymMembership.core.usecases.plan.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public CreatePlanCase createPlanCase(PlanGateway planGateway){
        return new CreatePlanCaseImpl(planGateway);
    }

    @Bean
    public ListPlanCase listPlanCase(PlanGateway planGateway){
        return new ListPlansCaseImpl(planGateway);
    }
    @Bean
    public ListByPlanCase listByPlanCase(PlanGateway planGateway){
        return new ListByPlanCaseImpl(planGateway);
    }
}
