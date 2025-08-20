package com.bsf.GymMembership.infrastructure.persistence.dto;

import java.time.LocalDate;
import java.util.UUID;

public record MemberDTO(
        UUID memberId,
        String name,
        String email,
        UUID planId,
        LocalDate startDate,
        LocalDate endDate,
        Boolean active
) {
}
