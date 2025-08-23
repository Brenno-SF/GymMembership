package com.bsf.GymMembership.infrastructure.gateway;

import com.bsf.GymMembership.core.entity.Member;
import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.gateway.MemberGateway;
import com.bsf.GymMembership.infrastructure.exception.NotFoundException;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.MemberEntity;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.PlanEntity;
import com.bsf.GymMembership.infrastructure.persistence.mapper.MemberEntityMapper;
import com.bsf.GymMembership.infrastructure.persistence.mapper.PlanEntityMapper;
import com.bsf.GymMembership.infrastructure.persistence.repository.MemberRepository;
import com.bsf.GymMembership.infrastructure.persistence.repository.PlanRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MemberRepositoryGateway implements MemberGateway {
    private final MemberRepository memberRepository;
    private final PlanRepository planRepository;
    private final MemberEntityMapper memberEntityMapper;

    public MemberRepositoryGateway(MemberRepository memberRepository, PlanRepository planRepository, MemberEntityMapper memberEntityMapper) {
        this.memberRepository = memberRepository;
        this.planRepository = planRepository;
        this.memberEntityMapper = memberEntityMapper;
    }


    @Override
    public Member createMember(Member member) {
        PlanEntity planEntity = planRepository.findById(member.planId()).orElseThrow(
                ()-> new NotFoundException("Plan Not Found")
        );

        MemberEntity memberEntity = memberEntityMapper.toEntity(member, planEntity);
        MemberEntity newMemberEntity =  memberRepository.save(memberEntity);

        return memberEntityMapper.toDomain(newMemberEntity);
    }

    @Override
    public Member updateMember(UUID memberId, Member member) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(
                ()-> new NotFoundException("Member Not Found")
        );
        PlanEntity planEntity = planRepository.findById(member.planId()).orElseThrow(
                ()-> new NotFoundException("Plan Not Found")
        );
        memberEntity.setName(member.name());
        memberEntity.setPlan(planEntity);
        memberEntity.setActive(member.active());
        memberEntity.setStartDate(member.startDate());
        memberEntity.setEndDate(member.endDate());
        memberEntity.setEmail(member.email());

        MemberEntity updatedMemberEntity =  memberRepository.save(memberEntity);
        return memberEntityMapper.toDomain(updatedMemberEntity);
    }

    @Override
    public void deleteMember(UUID memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(
                ()-> new NotFoundException("Member Not Found")
        );
        memberRepository.deleteById(memberId);
    }

    @Override
    public Member listMember(UUID memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(()-> new NotFoundException("Member Not Found"));

        return memberEntityMapper.toDomain(memberEntity);
    }

    @Override
    public List<Member> listAllMember() {
        return memberRepository.findAll()
                .stream()
                .map(memberEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}


