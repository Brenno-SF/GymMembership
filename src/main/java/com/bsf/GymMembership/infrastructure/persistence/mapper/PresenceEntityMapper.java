package com.bsf.GymMembership.infrastructure.persistence.mapper;

import com.bsf.GymMembership.core.entity.Presence;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.ClassEntity;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.MemberEntity;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.PresenceEntity;
import org.springframework.stereotype.Component;

@Component
public class PresenceEntityMapper {

    public PresenceEntity toEntity(Presence presence, MemberEntity member, ClassEntity gymClass) {
        return new PresenceEntity(
                presence.presenceId(),
                member,
                gymClass,
                presence.presence(),
                presence.register()
        );
    }

    public Presence toDomain(PresenceEntity entity) {
        return new Presence(
                entity.getPresenceId(),
                entity.getMember().getMemberId(),
                entity.getGymClass().getClassId(),
                entity.getPresence(),
                entity.getRegister()
        );
    }
}