package com.bsf.GymMembership.core.usecases.presence;

import com.bsf.GymMembership.core.entity.Presence;
import com.bsf.GymMembership.core.gateway.PresenceGateway;

import java.util.List;
import java.util.UUID;

public class ListPresenceByClassIdImpl implements  ListPresenceByClassId{
    private final PresenceGateway presenceGateway;

    public ListPresenceByClassIdImpl(PresenceGateway presenceGateway) {
        this.presenceGateway = presenceGateway;
    }

    @Override
    public List<Presence> execute(UUID classId) {
        return presenceGateway.listByClassId(classId);
    }
}
