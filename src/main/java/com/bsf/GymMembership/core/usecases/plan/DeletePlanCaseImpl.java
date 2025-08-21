package com.bsf.GymMembership.core.usecases.plan;

import com.bsf.GymMembership.core.gateway.PlanGateway;

import java.util.UUID;

public class DeletePlanCaseImpl implements DeletePlanCase{
    private final PlanGateway planGateway;

    public DeletePlanCaseImpl(PlanGateway planGateway) {
        this.planGateway = planGateway;
    }

    @Override
    public void execute(UUID planId) {
        planGateway.DeletePlanById(planId);
    }
}
