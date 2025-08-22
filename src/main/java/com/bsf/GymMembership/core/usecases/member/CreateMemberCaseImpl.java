package com.bsf.GymMembership.core.usecases.member;

import com.bsf.GymMembership.core.entity.Member;
import com.bsf.GymMembership.core.gateway.MemberGateway;

public class CreateMemberCaseImpl implements CreateMemberCase{
    private final MemberGateway memberGateway;

    public CreateMemberCaseImpl(MemberGateway memberGateway) {
        this.memberGateway = memberGateway;
    }

    @Override
    public Member execute(Member member) {
        return memberGateway.createMember(member);
    }
}
