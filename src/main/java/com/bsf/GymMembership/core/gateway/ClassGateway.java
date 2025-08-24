package com.bsf.GymMembership.core.gateway;

import com.bsf.GymMembership.core.entity.GymClass;

public interface ClassGateway {
    public GymClass createClass(GymClass gymClass);
}
