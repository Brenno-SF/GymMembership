package com.bsf.GymMembership.core.entity;

import java.time.LocalDate;

public record Member(
        String memberId,
        String name,
        String email,
        String planId,
        LocalDate startDate,
        LocalDate endDate,
        Boolean active
) {
}
