package com.bsf.GymMembership.infrastructure.persistence.mapper;


import com.bsf.GymMembership.core.entity.Member;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.MemberEntity;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.PlanEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberEntityMapper {

    public MemberEntity toEntity(Member member, PlanEntity planEntity) {
        return new MemberEntity(
                member.memberId(),
                member.name(),
                member.email(),
                planEntity,
                member.startDate(),
                member.endDate(),
                member.active()
        );
    }

    public Member toDomain(MemberEntity entity) {
        return new Member(
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
