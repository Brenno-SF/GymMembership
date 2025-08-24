package com.bsf.GymMembership.core.usecases.gymClass;

import com.bsf.GymMembership.core.entity.GymClass;

import java.util.Optional;
import java.util.UUID;

public interface ListClassCase {
    public Optional<GymClass> execute(UUID classId);
}
