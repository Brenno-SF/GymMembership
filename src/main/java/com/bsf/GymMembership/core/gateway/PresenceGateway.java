package com.bsf.GymMembership.core.gateway;

import com.bsf.GymMembership.core.entity.Presence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PresenceGateway {
    public Presence createPresence(Presence presence);
    public Optional<Presence> listById(UUID presenceId);
    public List<Presence> listByMemberId(UUID memberId);

}
