package com.bsf.GymMembership.core.gateway;

import com.bsf.GymMembership.core.entity.GymClass;

import java.util.List;

public interface ClassGateway {
    public GymClass createClass(GymClass gymClass);
    public List<GymClass> listAll();
}
