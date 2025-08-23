package com.bsf.GymMembership.core.usecases.member;

import com.bsf.GymMembership.core.entity.Member;

import java.util.List;

public interface ListAllMembersCase {
    public List<Member> execute();

}
