package com.bsf.GymMembership.core.usecases.gymClass;

import com.bsf.GymMembership.core.entity.GymClass;

import java.util.UUID;

public interface UpdateClassCase {
    public GymClass execute(UUID classId,GymClass gymClass);
}
