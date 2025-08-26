package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.entity.Member;
import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.usecases.member.*;
import com.bsf.GymMembership.infrastructure.persistence.dto.MemberDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.MemberDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("member")
public class MemberController {
    private final CreateMemberCase createMemberCase;
    private final UpdateMemberCase updateMemberCase;
    private final DeleteMemberCase deleteMemberCase;
    private final ListMemberCase listMemberCase;
    private final ListAllMembersCase listAllMembersCase;
    private final MemberDtoMapper mapper;

    public MemberController(CreateMemberCase createMemberCase, UpdateMemberCase updateMemberCase, DeleteMemberCase deleteMemberCase, ListMemberCase listMemberCase, ListAllMembersCase listAllMembersCase, MemberDtoMapper mapper) {
        this.createMemberCase = createMemberCase;
        this.updateMemberCase = updateMemberCase;
        this.deleteMemberCase = deleteMemberCase;
        this.listMemberCase = listMemberCase;
        this.listAllMembersCase = listAllMembersCase;
        this.mapper = mapper;
    }
    @PostMapping("create")
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO){
        Member member = mapper.toEntity(memberDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(createMemberCase.execute(member)));
    }
    @PutMapping("update/{memberId}")
    public ResponseEntity<MemberDTO> updateMember(@RequestBody MemberDTO memberDTO, @PathVariable UUID memberId){
        Member member = mapper.toEntity(memberDTO);

        return ResponseEntity.ok(mapper.toDto(updateMemberCase.execute(memberId, member)));
    }
    @DeleteMapping("delete/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable UUID memberId){
        deleteMemberCase.execute(memberId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("list/{memberId}")
    public ResponseEntity<MemberDTO> listMemberById(@PathVariable UUID memberId){
        return ResponseEntity.ok(mapper.toDto(listMemberCase.execute(memberId)));
    }
    @GetMapping("listAll")
    public ResponseEntity<List<MemberDTO>> listAllMembers(){
        return ResponseEntity.ok(listAllMembersCase.execute()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
    }

}
