package com.bsf.GymMembership.core.entity;

public record Plan(
        String planId,
        String name,
        String description,
        Integer maxCapacity,
        Integer durationMonth
) {
}
