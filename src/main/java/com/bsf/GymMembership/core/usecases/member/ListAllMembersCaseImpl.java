package com.bsf.GymMembership.core.usecases.member;

import com.bsf.GymMembership.core.entity.Member;
import com.bsf.GymMembership.core.gateway.MemberGateway;

import java.util.List;

public class ListAllMembersCaseImpl implements ListAllMembersCase{
    private final MemberGateway memberGateway;

    public ListAllMembersCaseImpl(MemberGateway memberGateway) {
        this.memberGateway = memberGateway;
    }

    @Override
    public List<Member> execute() {
        return memberGateway.listAllMember();
    }
}
