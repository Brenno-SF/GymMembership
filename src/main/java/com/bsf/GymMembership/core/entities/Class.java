package com.bsf.GymMembership.core.entities;

import java.time.LocalDateTime;

public record Class(
        String classId,
        String name,
        String description,
        LocalDateTime dateHour
) {
}
