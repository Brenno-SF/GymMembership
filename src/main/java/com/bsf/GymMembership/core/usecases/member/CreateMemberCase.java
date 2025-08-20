package com.bsf.GymMembership.core.usecases.member;

import com.bsf.GymMembership.core.entity.Member;

public interface CreateMemberCase {
    public Member execute(Member member);
}
