package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.entity.Member;
import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.usecases.member.CreateMemberCase;
import com.bsf.GymMembership.core.usecases.member.UpdateMemberCase;
import com.bsf.GymMembership.infrastructure.persistence.dto.MemberDTO;
import com.bsf.GymMembership.infrastructure.persistence.dto.PlanDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.MemberDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("member")
public class MemberController {
    private final CreateMemberCase createMemberCase;
    private final UpdateMemberCase updateMemberCase;
    private final MemberDtoMapper mapper;

    public MemberController(CreateMemberCase createMemberCase, UpdateMemberCase updateMemberCase, MemberDtoMapper mapper) {
        this.createMemberCase = createMemberCase;
        this.updateMemberCase = updateMemberCase;
        this.mapper = mapper;
    }
    @PostMapping("create")
    public MemberDTO createMember(@RequestBody MemberDTO memberDTO){
        Member member = mapper.toEntity(memberDTO);

        return mapper.toDto(createMemberCase.execute(member));
    }
    @PutMapping("update/{memberId}")
    public MemberDTO updateMember(@RequestBody MemberDTO memberDTO, @PathVariable UUID memberId){
        Member member = mapper.toEntity(memberDTO);

        return mapper.toDto(updateMemberCase.execute(memberId, member));
    }

}
