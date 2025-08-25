package com.bsf.GymMembership.core.usecases.presence;

import com.bsf.GymMembership.core.entity.Presence;

import java.util.Optional;
import java.util.UUID;

public interface ListPresenceById {
    public Optional<Presence> execute(UUID presenceId);
}
