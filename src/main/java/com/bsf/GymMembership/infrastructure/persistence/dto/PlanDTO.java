package com.bsf.GymMembership.infrastructure.persistence.dto;

import java.util.UUID;

public record PlanDTO(
        UUID planId,
        String name,
        String description,
        Integer maxCapacity,
        Integer durationMonth
) {
}
