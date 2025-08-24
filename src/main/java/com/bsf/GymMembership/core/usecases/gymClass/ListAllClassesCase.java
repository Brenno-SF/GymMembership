package com.bsf.GymMembership.core.usecases.gymClass;

import com.bsf.GymMembership.core.entity.GymClass;

import java.util.List;

public interface ListAllClassesCase {
    public List<GymClass> execute();
}
