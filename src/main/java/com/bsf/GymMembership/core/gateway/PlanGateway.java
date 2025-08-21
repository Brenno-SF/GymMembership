package com.bsf.GymMembership.core.gateway;

import com.bsf.GymMembership.core.entity.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanGateway {
    public Plan createPlan(Plan plan);
    public List<Plan> listPlans();
    public Optional<Plan> listByPlan(String plan);
}
