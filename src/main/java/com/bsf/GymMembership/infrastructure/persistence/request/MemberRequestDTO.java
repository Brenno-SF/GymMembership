package com.bsf.GymMembership.infrastructure.persistence.request;

import java.time.LocalDate;

public record MemberRequestDTO(
        String name,
        String email,
        String planId,
        LocalDate startDate,
        LocalDate endDate,
        Boolean active
) {
}
