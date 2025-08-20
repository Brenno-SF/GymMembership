package com.bsf.GymMembership.core.entity;

import java.time.LocalDate;
import java.util.UUID;

public record Presence(
        UUID presenceId,
        UUID memberId,
        UUID classId,
        Boolean presence,
        LocalDate register
) {
}
