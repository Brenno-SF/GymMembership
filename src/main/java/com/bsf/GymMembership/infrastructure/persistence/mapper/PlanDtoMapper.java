package com.bsf.GymMembership.infrastructure.persistence.mapper;
import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.infrastructure.persistence.dto.PlanDTO;
import org.springframework.stereotype.Component;

@Component
public class PlanDtoMapper {

    public Plan toEntity(PlanDTO dto) {
        return new Plan(
                dto.planId(),
                dto.name(),
                dto.description(),
                dto.maxCapacity(),
                dto.durationMonth()
        );
    }

    public PlanDTO toDto(Plan entity) {
        return new PlanDTO(
                entity.planId(),
                entity.name(),
                entity.description(),
                entity.maxCapacity(),
                entity.durationMonth()
        );
    }
}