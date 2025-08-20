package com.bsf.GymMembership.infrastructure.gateway;

import com.bsf.GymMembership.core.gateway.PlanGateway;
import com.bsf.GymMembership.infrastructure.persistence.repository.PlanRepository;
import org.springframework.stereotype.Component;

@Component
public class PlanRepositoryGateway implements PlanGateway {
    private final PlanRepository planRepository;

    public PlanRepositoryGateway(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }
}
