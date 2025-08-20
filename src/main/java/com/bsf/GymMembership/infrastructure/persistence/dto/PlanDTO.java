package com.bsf.GymMembership.infrastructure.persistence.dto;

public record PlanDTO(
        String planId,
        String name,
        String description,
        Integer maxCapacity,
        Integer durationMonth
) {
}
