package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.entity.Member;
import com.bsf.GymMembership.core.usecases.member.CreateMemberCase;
import com.bsf.GymMembership.infrastructure.persistence.dto.MemberDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.MemberDtoMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
public class MemberController {
    private final CreateMemberCase createMemberCase;
    private final MemberDtoMapper mapper;

    public MemberController(CreateMemberCase createMemberCase, MemberDtoMapper mapper) {
        this.createMemberCase = createMemberCase;
        this.mapper = mapper;
    }
    @PostMapping("create")
    public MemberDTO createMember(@RequestBody MemberDTO memberDTO){
        Member member = mapper.toEntity(memberDTO);

        return mapper.toDto(createMemberCase.execute(member));
    }

}
