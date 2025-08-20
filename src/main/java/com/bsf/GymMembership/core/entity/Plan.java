package com.bsf.GymMembership.core.entity;

import java.util.UUID;

public record Plan(
        UUID planId,
        String name,
        String description,
        Integer maxCapacity,
        Integer durationMonth
) {
}
