package com.bsf.GymMembership.core.usecases;

import com.bsf.GymMembership.core.entities.Member;

public interface CreateMemberCase {
    public Member execute(Member member);
}
