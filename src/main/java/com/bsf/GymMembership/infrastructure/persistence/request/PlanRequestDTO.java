package com.bsf.GymMembership.infrastructure.persistence.request;

public record PlanRequestDTO(
        String name,
        String description,
        Integer maxCapacity,
        Integer durationMonth
) {
}
