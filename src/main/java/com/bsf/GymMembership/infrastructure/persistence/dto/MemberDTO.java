package com.bsf.GymMembership.infrastructure.persistence.dto;

import java.time.LocalDate;

public record MemberDTO(
        String memberId,
        String name,
        String email,
        String planId,
        LocalDate startDate,
        LocalDate endDate,
        Boolean active
) {
}
