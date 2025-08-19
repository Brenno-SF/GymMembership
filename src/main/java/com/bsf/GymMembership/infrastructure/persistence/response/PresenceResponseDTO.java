package com.bsf.GymMembership.infrastructure.persistence.response;

import java.time.LocalDate;

public record PresenceResponseDTO(
        String presenceId,
        String memberId,
        String classId,
        Boolean presence,
        LocalDate register
) {
}
