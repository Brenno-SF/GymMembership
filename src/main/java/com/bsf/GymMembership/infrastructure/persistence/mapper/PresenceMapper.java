package com.bsf.GymMembership.infrastructure.persistence.mapper;

import com.bsf.GymMembership.core.entity.Presence;
import com.bsf.GymMembership.infrastructure.persistence.dto.PresenceDTO;
import org.springframework.stereotype.Component;

@Component
public class PresenceMapper {

    public static Presence toEntity(PresenceDTO dto) {
        return new Presence(
                dto.presenceId(),
                dto.memberId(),
                dto.classId(),
                dto.presence(),
                dto.register()
        );
    }

    public static PresenceDTO toDto(Presence entity) {
        return new PresenceDTO(
                entity.presenceId(),
                entity.memberId(),
                entity.classId(),
                entity.presence(),
                entity.register()
        );
    }
}