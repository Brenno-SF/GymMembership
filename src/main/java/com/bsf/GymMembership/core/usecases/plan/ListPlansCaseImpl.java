package com.bsf.GymMembership.core.usecases.plan;

import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.gateway.PlanGateway;

import java.util.List;

public class ListPlansCaseImpl implements ListPlanCase{
    private final PlanGateway planGateway;

    public ListPlansCaseImpl(PlanGateway planGateway) {
        this.planGateway = planGateway;
    }

    @Override
    public List<Plan> execute() {
        return planGateway.listPlans();
    }
}
