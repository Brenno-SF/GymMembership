package com.bsf.GymMembership.infrastructure.persistence.mapper;

import com.bsf.GymMembership.infrastructure.persistence.entitiy.ClassEntity;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.MemberEntity;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.PresenceEntity;
import com.bsf.GymMembership.infrastructure.persistence.request.PresenceRequestDTO;
import com.bsf.GymMembership.infrastructure.persistence.response.PresenceResponseDTO;

public class PresenceMapper {

    public static PresenceEntity toEntity(PresenceRequestDTO dto, MemberEntity member, ClassEntity classEntity) {
        PresenceEntity presence = new PresenceEntity();
        presence.setMember(member);
        presence.setGymClass(classEntity);
        presence.setPresence(dto.presence());
        presence.setRegister(dto.register());
        return presence;
    }

    public static PresenceResponseDTO toDto(PresenceEntity entity) {
        return new PresenceResponseDTO(
                entity.getPresenceId(),
                entity.getMember().getMemberId(),
                entity.getGymClass().getClassId(),
                entity.getPresence(),
                entity.getRegister()
        );
    }
}