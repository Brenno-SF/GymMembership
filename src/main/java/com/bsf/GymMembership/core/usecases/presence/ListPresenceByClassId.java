package com.bsf.GymMembership.core.usecases.presence;

import com.bsf.GymMembership.core.entity.Presence;

import java.util.List;
import java.util.UUID;

public interface ListPresenceByClassId {
    public List<Presence> execute(UUID classId);
}
