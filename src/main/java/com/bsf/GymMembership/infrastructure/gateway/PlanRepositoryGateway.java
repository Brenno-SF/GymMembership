package com.bsf.GymMembership.infrastructure.gateway;

import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.gateway.PlanGateway;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.PlanEntity;
import com.bsf.GymMembership.infrastructure.persistence.mapper.PlanEntityMapper;
import com.bsf.GymMembership.infrastructure.persistence.repository.PlanRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlanRepositoryGateway implements PlanGateway {
    private final PlanRepository planRepository;
    private final PlanEntityMapper planEntityMapper;

    public PlanRepositoryGateway(PlanRepository planRepository, PlanEntityMapper planEntityMapper) {
        this.planRepository = planRepository;
        this.planEntityMapper = planEntityMapper;
    }

    @Override
    public Plan createPlan(Plan plan) {
        PlanEntity entity = planEntityMapper.toEntity(plan);
        PlanEntity newPlan = planRepository.save(entity);
        return planEntityMapper.toDomain(newPlan);
    }

    @Override
    public List<Plan> listPlans() {
        return  planRepository.findAll()
                .stream()
                .map(planEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
