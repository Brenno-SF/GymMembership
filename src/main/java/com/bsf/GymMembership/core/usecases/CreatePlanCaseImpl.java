package com.bsf.GymMembership.core.usecases;

import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.gateway.PlanGateway;

public class CreatePlanCaseImpl implements CreatePlanCase{
    private final PlanGateway planGateway;

    public CreatePlanCaseImpl(PlanGateway planGateway) {
        this.planGateway = planGateway;
    }

    @Override
    public Plan execute(Plan plan) {
        return null;
    }
}
