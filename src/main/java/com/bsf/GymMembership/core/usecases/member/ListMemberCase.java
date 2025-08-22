package com.bsf.GymMembership.core.usecases.member;

import com.bsf.GymMembership.core.entity.Member;

import java.util.UUID;

public interface ListMemberCase {
    public Member execute(UUID memberId);
}
