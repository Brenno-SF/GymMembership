package com.bsf.GymMembership.core.usecases.presence;

import com.bsf.GymMembership.core.entity.Presence;
import com.bsf.GymMembership.core.gateway.PresenceGateway;

import java.util.List;
import java.util.UUID;

public class ListPresenceByMemberIdImpl implements  ListPresenceByMemberId{
    private final PresenceGateway presenceGateway;

    public ListPresenceByMemberIdImpl(PresenceGateway presenceGateway) {
        this.presenceGateway = presenceGateway;
    }

    @Override
    public List<Presence> execute(UUID memberId) {
        return presenceGateway.listByMemberId(memberId);
    }
}
