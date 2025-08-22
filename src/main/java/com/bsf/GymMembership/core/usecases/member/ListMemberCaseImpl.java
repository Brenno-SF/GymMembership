package com.bsf.GymMembership.core.usecases.member;

import com.bsf.GymMembership.core.entity.Member;
import com.bsf.GymMembership.core.gateway.MemberGateway;

import java.util.UUID;

public class ListMemberCaseImpl implements ListMemberCase{
    private final MemberGateway memberGateway;

    public ListMemberCaseImpl(MemberGateway memberGateway) {
        this.memberGateway = memberGateway;
    }

    @Override
    public Member execute(UUID memberId) {
        return memberGateway.listMember(memberId);
    }
}
