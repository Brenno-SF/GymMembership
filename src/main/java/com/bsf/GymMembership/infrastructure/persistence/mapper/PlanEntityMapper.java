package com.bsf.GymMembership.infrastructure.persistence.mapper;

import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.PlanEntity;
import org.springframework.stereotype.Component;

@Component
public class PlanEntityMapper  {
    public PlanEntity toEntity(Plan plan){
        return new PlanEntity(
                plan.planId(),
                plan.name(),
                plan.description(),
                plan.maxCapacity(),
                plan.durationMonth()
        );
    }
    public Plan toDomain(PlanEntity planEntity){
        return new Plan(
                planEntity.getPlanId(),
                planEntity.getNamePlan(),
                planEntity.getDescriptionPlan(),
                planEntity.getMaxCapacity(),
                planEntity.getDurationMonth()
        );
    }
}
