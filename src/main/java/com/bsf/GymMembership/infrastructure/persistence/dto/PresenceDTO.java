package com.bsf.GymMembership.infrastructure.persistence.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PresenceDTO(
        UUID presenceId,
        UUID memberId,
        UUID classId,
        Boolean presence,
        LocalDate register
) {
}
