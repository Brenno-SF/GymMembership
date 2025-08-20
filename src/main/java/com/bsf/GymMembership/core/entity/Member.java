package com.bsf.GymMembership.core.entity;

import java.time.LocalDate;
import java.util.UUID;

public record Member(
        UUID memberId,
        String name,
        String email,
        UUID planId,
        LocalDate startDate,
        LocalDate endDate,
        Boolean active
) {
}
