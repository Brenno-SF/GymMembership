package com.bsf.GymMembership.core.gateway;

import com.bsf.GymMembership.core.entity.GymClass;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClassGateway {
    public GymClass createClass(GymClass gymClass);
    public List<GymClass> listAll();
    public Optional<GymClass> listById(UUID classId);
}
