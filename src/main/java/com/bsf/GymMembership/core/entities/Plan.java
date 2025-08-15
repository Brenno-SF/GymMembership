package com.bsf.GymMembership.core.entities;

public record Plan(
        String planId,
        String Name,
        String description,
        Integer maxCapacity,
        Integer durationMonth
) {
}
