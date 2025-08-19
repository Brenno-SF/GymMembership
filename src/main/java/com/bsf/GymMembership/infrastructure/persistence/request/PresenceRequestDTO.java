package com.bsf.GymMembership.infrastructure.persistence.request;

import java.time.LocalDate;

public record PresenceRequestDTO(
        String memberId,
        String classId,
        Boolean presence,
        LocalDate register
) {
}
