package com.bsf.GymMembership.infrastructure.gateway;

import com.bsf.GymMembership.core.entity.Presence;
import com.bsf.GymMembership.core.gateway.PresenceGateway;
import com.bsf.GymMembership.infrastructure.exception.NotFoundException;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.ClassEntity;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.MemberEntity;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.PlanEntity;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.PresenceEntity;
import com.bsf.GymMembership.infrastructure.persistence.mapper.ClassEntityMapper;
import com.bsf.GymMembership.infrastructure.persistence.mapper.PresenceEntityMapper;
import com.bsf.GymMembership.infrastructure.persistence.repository.ClassRepository;
import com.bsf.GymMembership.infrastructure.persistence.repository.MemberRepository;
import com.bsf.GymMembership.infrastructure.persistence.repository.PlanRepository;
import com.bsf.GymMembership.infrastructure.persistence.repository.PresenceRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PresenceRepositoryGateway implements PresenceGateway {
    private final PresenceRepository presenceRepository;
    private final MemberRepository memberRepository;
    private final ClassRepository classRepository;
    private final PresenceEntityMapper presenceEntityMapper;


    public PresenceRepositoryGateway(PresenceRepository presenceRepository, MemberRepository memberRepository, ClassRepository classRepository, PresenceEntityMapper presenceEntityMapper) {
        this.presenceRepository = presenceRepository;
        this.memberRepository = memberRepository;
        this.classRepository = classRepository;
        this.presenceEntityMapper = presenceEntityMapper;
    }


    @Override
    public Presence createPresence(Presence presence) {
        MemberEntity memberEntity = memberRepository.findById(presence.memberId()).orElseThrow(
                ()-> new NotFoundException("Member Not Found")
        );
        ClassEntity classEntity = classRepository.findById(presence.classId()).orElseThrow(
                ()-> new NotFoundException("Class Not Found")
        );

        PresenceEntity presenceEntity = presenceEntityMapper.toEntity(presence, memberEntity,classEntity);
        PresenceEntity newPresenceEntity = presenceRepository.save(presenceEntity);

        return presenceEntityMapper.toDomain(newPresenceEntity);
    }

    @Override
    public Optional<Presence> listById(UUID presenceId) {
        return presenceRepository.findById(presenceId)
                .map(presenceEntityMapper::toDomain);
    }

    @Override
    public List<Presence> listByMemberId(UUID memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(
                ()-> new NotFoundException("Member Not Found")
        );

        return presenceRepository.findAllByMember_MemberId(memberId)
                .stream()
                .map(presenceEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Presence> listByClassId(UUID classId) {
        ClassEntity classEntity = classRepository.findById(classId).orElseThrow(
                ()-> new NotFoundException("Class Not Found")
        );

        return presenceRepository.findAllByGymClass_ClassId(classId)
                .stream()
                .map(presenceEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
