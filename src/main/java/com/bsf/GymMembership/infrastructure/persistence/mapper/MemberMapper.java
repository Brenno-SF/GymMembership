package com.bsf.GymMembership.infrastructure.persistence.mapper;

import com.bsf.GymMembership.infrastructure.persistence.entitiy.MemberEntity;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.PlanEntity;
import com.bsf.GymMembership.infrastructure.persistence.request.MemberRequestDTO;
import com.bsf.GymMembership.infrastructure.persistence.response.MemberResponseDTO;

public class MemberMapper {
    public static MemberEntity toEntity(MemberRequestDTO dto, PlanEntity plan){
        MemberEntity member = new MemberEntity();
        member.setName(dto.name());
        member.setEmail(dto.email());
        member.setActive(dto.active());
        member.setStartDate(dto.startDate());
        member.setEndDate(dto.endDate());
        member.setPlan(plan);

        return member;
    }
    public static MemberResponseDTO  toDto(MemberEntity entity){
        return new MemberResponseDTO(
                entity.getMemberId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPlan().getPlanId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getActive()
        );
    }
}

