package com.bsf.GymMembership.core.usecases.member;

import com.bsf.GymMembership.core.gateway.MemberGateway;

import java.util.UUID;

public class DeleteMemberCaseImpl implements DeleteMemberCase{
    private final MemberGateway memberGateway;

    public DeleteMemberCaseImpl(MemberGateway memberGateway) {
        this.memberGateway = memberGateway;
    }

    @Override
    public void execute(UUID memberId) {
        memberGateway.deleteMember(memberId);
    }
}
