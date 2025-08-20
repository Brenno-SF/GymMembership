package com.bsf.GymMembership.infrastructure.gateway;

import com.bsf.GymMembership.core.gateway.PresenceGateway;
import com.bsf.GymMembership.infrastructure.persistence.repository.PlanRepository;
import org.springframework.stereotype.Component;

@Component
public class PresenceRepositoryGateway implements PresenceGateway {
    private final PlanRepository planRepository;

    public PresenceRepositoryGateway(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }
}
