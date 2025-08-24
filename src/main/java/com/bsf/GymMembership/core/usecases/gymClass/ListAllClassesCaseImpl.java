package com.bsf.GymMembership.core.usecases.gymClass;

import com.bsf.GymMembership.core.entity.GymClass;
import com.bsf.GymMembership.core.gateway.ClassGateway;

import java.util.List;

public class ListAllClassesCaseImpl implements ListAllClassesCase{
    private final ClassGateway classGateway;

    public ListAllClassesCaseImpl(ClassGateway classGateway) {
        this.classGateway = classGateway;
    }

    @Override
    public List<GymClass> execute() {
        return classGateway.listAll();
    }
}
