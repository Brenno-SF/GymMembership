package com.bsf.GymMembership.core.usecases.gymClass;

import com.bsf.GymMembership.core.gateway.ClassGateway;

import java.util.UUID;

public class DeleteClassCaseImpl implements DeleteClassCase{
    private final ClassGateway classGateway;

    public DeleteClassCaseImpl(ClassGateway classGateway) {
        this.classGateway = classGateway;
    }

    @Override
    public void execute(UUID classId) {
        classGateway.deleteById(classId);
    }
}
