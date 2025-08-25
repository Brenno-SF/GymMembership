package com.bsf.GymMembership.core.usecases.presence;

import com.bsf.GymMembership.core.entity.Presence;
import com.bsf.GymMembership.core.gateway.PresenceGateway;

import java.util.Optional;
import java.util.UUID;

public class ListPresenceByIdImpl implements ListPresenceById{
    private final PresenceGateway presenceGateway;

    public ListPresenceByIdImpl(PresenceGateway presenceGateway) {
        this.presenceGateway = presenceGateway;
    }

    @Override
    public Optional<Presence> execute(UUID presenceId) {
        return presenceGateway.listById(presenceId);
    }
}
