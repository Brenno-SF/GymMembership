package com.bsf.GymMembership.core.usecases.plan;

import com.bsf.GymMembership.core.entity.Plan;

import java.util.Optional;

public interface ListByPlanCase {
    Optional<Plan> execute(String plan);
}
