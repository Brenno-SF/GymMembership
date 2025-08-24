package com.bsf.GymMembership.core.usecases.gymClass;

import java.util.UUID;

public interface DeleteClassCase {
    public void execute(UUID classId);
}
