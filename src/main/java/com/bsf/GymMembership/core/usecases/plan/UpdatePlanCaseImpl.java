package com.bsf.GymMembership.core.usecases.plan;

import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.gateway.PlanGateway;

import java.util.UUID;

public class UpdatePlanCaseImpl implements UpdatePlanCase{

    private final PlanGateway planGateway;

    public UpdatePlanCaseImpl(PlanGateway planGateway) {
        this.planGateway = planGateway;
    }

    @Override
    public Plan execute(UUID planId, Plan plan) {
        return planGateway.updatePlanById(planId, plan);
    }
}
