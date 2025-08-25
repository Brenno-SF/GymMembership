package com.bsf.GymMembership.core.usecases.presence;

import com.bsf.GymMembership.core.entity.Presence;

public interface CreatePresenceCase {
    public Presence execute(Presence presence);
}
