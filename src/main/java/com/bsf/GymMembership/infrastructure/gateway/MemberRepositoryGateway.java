package com.bsf.GymMembership.infrastructure.gateway;

import com.bsf.GymMembership.core.gateway.MemberGateway;
import com.bsf.GymMembership.infrastructure.persistence.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberRepositoryGateway implements MemberGateway {
    private final MemberRepository memberRepository;

    public MemberRepositoryGateway(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
