package com.bsf.GymMembership.core.entities;

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
