package com.bsf.GymMembership.infrastructure.persistence.response;

public record PlanResponseDTO(
        String planId,
        String Name,
        String description,
        Integer maxCapacity,
        Integer durationMonth
) {
}
