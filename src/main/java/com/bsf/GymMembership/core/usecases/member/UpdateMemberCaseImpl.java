package com.bsf.GymMembership.core.usecases.member;

import com.bsf.GymMembership.core.entity.Member;
import com.bsf.GymMembership.core.gateway.MemberGateway;

import java.util.UUID;

public class UpdateMemberCaseImpl implements UpdateMemberCase {
    private final MemberGateway memberGateway;

    public UpdateMemberCaseImpl(MemberGateway memberGateway) {
        this.memberGateway = memberGateway;
    }

    @Override
    public Member execute(UUID memberId, Member member) {
        return memberGateway.updateMember(memberId, member);
    }
}
