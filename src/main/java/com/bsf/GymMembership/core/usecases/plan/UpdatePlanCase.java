package com.bsf.GymMembership.core.usecases.plan;

import com.bsf.GymMembership.core.entity.Plan;

import java.util.UUID;

public interface UpdatePlanCase {
    public Plan execute(UUID planId, Plan plan);
}
