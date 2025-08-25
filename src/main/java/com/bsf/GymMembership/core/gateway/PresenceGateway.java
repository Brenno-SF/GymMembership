package com.bsf.GymMembership.core.gateway;

import com.bsf.GymMembership.core.entity.Presence;

public interface PresenceGateway {
    public Presence createPresence(Presence presence);
}
