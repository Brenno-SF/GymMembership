package com.bsf.GymMembership.core.usecases.gymClass;

import com.bsf.GymMembership.core.entity.GymClass;
import com.bsf.GymMembership.core.gateway.ClassGateway;

public class CreateClassCaseImpl implements CreateClassCase{
    private final ClassGateway classGateway;

    public CreateClassCaseImpl(ClassGateway classGateway) {
        this.classGateway = classGateway;
    }

    @Override
    public GymClass execute(GymClass gymClass) {
        return classGateway.createClass(gymClass);
    }
}
