package com.bsf.GymMembership.infrastructure.persistence.mapper;

import com.bsf.GymMembership.core.entity.Member;
import com.bsf.GymMembership.infrastructure.persistence.dto.MemberDTO;
import org.springframework.stereotype.Component;


@Component
public class MemberMapper {
    public static Member toEntity(MemberDTO dto){
        return new Member(
                dto.memberId(),
                dto.name(),
                dto.email(),
                dto.planId(),
                dto.startDate(),
                dto.endDate(),
                dto.active()
        );
    }
    public static MemberDTO toDto(Member entity){
        return new MemberDTO(
                entity.memberId(),
                entity.name(),
                entity.email(),
                entity.planId(),
                entity.startDate(),
                entity.endDate(),
                entity.active()
        );
    }
}

