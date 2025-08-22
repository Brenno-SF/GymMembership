package com.bsf.GymMembership.core.usecases.member;

import java.util.UUID;

public interface DeleteMemberCase {
    public void execute(UUID memberId);
}
