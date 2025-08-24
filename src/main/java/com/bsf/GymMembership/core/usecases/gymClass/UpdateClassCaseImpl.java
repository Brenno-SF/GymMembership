package com.bsf.GymMembership.core.usecases.gymClass;

import com.bsf.GymMembership.core.entity.GymClass;
import com.bsf.GymMembership.core.gateway.ClassGateway;

import java.util.UUID;

public class UpdateClassCaseImpl implements UpdateClassCase{
    private final ClassGateway classGateway;

    public UpdateClassCaseImpl(ClassGateway classGateway) {
        this.classGateway = classGateway;
    }

    @Override
    public GymClass execute(UUID classId, GymClass gymClass) {
        return classGateway.updateById(classId, gymClass);
    }
}
