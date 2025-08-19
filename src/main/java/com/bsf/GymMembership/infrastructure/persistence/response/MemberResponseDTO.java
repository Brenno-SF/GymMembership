package com.bsf.GymMembership.infrastructure.persistence.response;

import java.time.LocalDate;

public record MemberResponseDTO(
        String memberId,
        String name,
        String email,
        String planId,
        LocalDate startDate,
        LocalDate endDate,
        Boolean active
) {
}
