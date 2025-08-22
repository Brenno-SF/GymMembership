package com.bsf.GymMembership.core.gateway;

import com.bsf.GymMembership.core.entity.Member;

import java.util.UUID;

public interface MemberGateway {
    public Member createMember(Member member);
    public Member updateMember(UUID memberId, Member member);
    public void deleteMember(UUID memberId);
    public Member listMember(UUID memberId);
}
