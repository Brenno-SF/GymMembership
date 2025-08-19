package com.bsf.GymMembership.infrastructure.persistence.mapper;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.PlanEntity;
import com.bsf.GymMembership.infrastructure.persistence.request.PlanRequestDTO;
import com.bsf.GymMembership.infrastructure.persistence.response.PlanResponseDTO;

public class PlanMapper {

    public static PlanEntity toEntity(PlanRequestDTO dto) {
        PlanEntity plan = new PlanEntity();
        plan.setNamePlan(dto.name());
        plan.setDescriptionPlan(dto.description());
        plan.setMaxCapacity(dto.maxCapacity());
        plan.setDurationMonth(dto.durationMonth());
        return plan;
    }

    public static PlanResponseDTO toDto(PlanEntity entity) {
        return new PlanResponseDTO(
                entity.getPlanId(),
                entity.getNamePlan(),
                entity.getDescriptionPlan(),
                entity.getMaxCapacity(),
                entity.getDurationMonth()
        );
    }
}