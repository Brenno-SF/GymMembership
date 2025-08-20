package com.bsf.GymMembership.infrastructure.persistence.dto;

import java.time.LocalDate;

public record PresenceDTO(
        String presenceId,
        String memberId,
        String classId,
        Boolean presence,
        LocalDate register
) {
}
