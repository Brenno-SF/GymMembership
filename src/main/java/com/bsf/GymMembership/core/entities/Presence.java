package com.bsf.GymMembership.core.entities;

import java.time.LocalDate;

public record Presence(
        String presenceId,
        String memberId,
        String classId,
        Boolean presence,
        LocalDate register
) {
}
