package com.bsf.GymMembership.infrastructure.bean;

import com.bsf.GymMembership.core.gateway.MemberGateway;
import com.bsf.GymMembership.core.gateway.PlanGateway;
import com.bsf.GymMembership.core.usecases.member.*;
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
    @Bean
    public UpdatePlanCase updatePlanCase(PlanGateway planGateway){
        return new UpdatePlanCaseImpl(planGateway);
    }
    @Bean
    public DeletePlanCase deletePlanCase(PlanGateway planGateway){
        return new DeletePlanCaseImpl(planGateway);
    }

    @Bean
    public CreateMemberCase createMemberCase(MemberGateway memberGateway){
        return new CreateMemberCaseImpl(memberGateway);
    }
    @Bean
    public UpdateMemberCase updateMemberCase(MemberGateway memberGateway){
        return new UpdateMemberCaseImpl(memberGateway);
    }
    @Bean
    public ListMemberCase listMemberCase(MemberGateway memberGateway){
        return new ListMemberCaseImpl(memberGateway);
    }
    @Bean
    public DeleteMemberCase deleteMemberCase(MemberGateway memberGateway){
        return new DeleteMemberCaseImpl(memberGateway);
    }
}
