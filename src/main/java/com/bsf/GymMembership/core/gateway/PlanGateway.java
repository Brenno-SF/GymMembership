package com.bsf.GymMembership.core.gateway;

import com.bsf.GymMembership.core.entity.Plan;

import java.util.List;

public interface PlanGateway {
    public Plan createPlan(Plan plan);
    public List<Plan> listPlans();
}
