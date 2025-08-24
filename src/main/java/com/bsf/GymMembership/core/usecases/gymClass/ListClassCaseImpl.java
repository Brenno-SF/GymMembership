package com.bsf.GymMembership.core.usecases.gymClass;

import com.bsf.GymMembership.core.entity.GymClass;
import com.bsf.GymMembership.core.gateway.ClassGateway;

import java.util.Optional;
import java.util.UUID;

public class ListClassCaseImpl implements ListClassCase{
    private final ClassGateway classGateway;

    public ListClassCaseImpl(ClassGateway classGateway) {
        this.classGateway = classGateway;
    }

    @Override
    public Optional<GymClass> execute(UUID classId) {
        return classGateway.listById(classId);
    }
}
