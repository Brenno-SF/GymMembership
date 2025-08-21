package com.bsf.GymMembership.infrastructure.gateway;

import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.gateway.PlanGateway;
import com.bsf.GymMembership.infrastructure.exception.NotFoundException;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.PlanEntity;
import com.bsf.GymMembership.infrastructure.persistence.mapper.PlanEntityMapper;
import com.bsf.GymMembership.infrastructure.persistence.repository.PlanRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    @Override
    public Optional<Plan> listByPlan(String plan) {
        return planRepository.findByNamePlan(plan)
                .map(planEntityMapper::toDomain);
    }

    @Override
    public Plan updatePlanById(UUID planId, Plan plan) {
        PlanEntity planEntity =  planRepository.findById(planId)
                .orElseThrow(()-> new NotFoundException("Plan not Found"));

        planEntity.setNamePlan(plan.name());
        planEntity.setDescriptionPlan(plan.description());
        planEntity.setDurationMonth(plan.durationMonth());
        planEntity.setMaxCapacity(plan.maxCapacity());

        PlanEntity updatedPlanEntity =  planRepository.save(planEntity);

        return planEntityMapper.toDomain(updatedPlanEntity);
    }

    @Override
    public void DeletePlanById(UUID planId) {
        PlanEntity planEntity =  planRepository.findById(planId)
                .orElseThrow(()-> new NotFoundException("Plan not Found"));

        planRepository.delete(planEntity);
    }
}
