package com.bsf.GymMembership.core.usecases.plan;

import java.util.UUID;

public interface DeletePlanCase {
    public void execute(UUID planId);
}
