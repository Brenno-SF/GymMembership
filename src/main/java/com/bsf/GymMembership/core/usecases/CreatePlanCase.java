package com.bsf.GymMembership.core.usecases;

import com.bsf.GymMembership.core.entity.Plan;

public interface CreatePlanCase {
    public Plan execute(Plan plan);
}
