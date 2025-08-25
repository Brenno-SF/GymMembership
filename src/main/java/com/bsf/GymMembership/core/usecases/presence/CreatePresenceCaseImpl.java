package com.bsf.GymMembership.core.usecases.presence;

import com.bsf.GymMembership.core.entity.Presence;
import com.bsf.GymMembership.core.gateway.PresenceGateway;

public class CreatePresenceCaseImpl implements CreatePresenceCase{
    private final PresenceGateway presenceGateway;

    public CreatePresenceCaseImpl(PresenceGateway presenceGateway) {
        this.presenceGateway = presenceGateway;
    }

    @Override
    public Presence execute(Presence presence) {
        return presenceGateway.createPresence(presence);
    }
}
