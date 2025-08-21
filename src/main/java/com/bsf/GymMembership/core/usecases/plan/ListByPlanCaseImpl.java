package com.bsf.GymMembership.core.usecases.plan;

import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.gateway.PlanGateway;

import java.util.List;
import java.util.Optional;

public class ListByPlanCaseImpl implements ListByPlanCase{
    private final PlanGateway planGateway;

    public ListByPlanCaseImpl(PlanGateway planGateway) {
        this.planGateway = planGateway;
    }

    @Override
    public Optional<Plan> execute(String plan) {
        return planGateway.listByPlan(plan);
    }
}
