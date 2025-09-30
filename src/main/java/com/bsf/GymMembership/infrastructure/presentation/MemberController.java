package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.entity.Member;
import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.usecases.member.*;
import com.bsf.GymMembership.infrastructure.persistence.dto.MemberDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.MemberDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("member")
@Tag(name = "Members", description = "Operations related to gym members")
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

    @Operation(summary = "Create a new member", description = "Adds a new member to the gym")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Member successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @PostMapping("create")
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO){
        Member member = mapper.toEntity(memberDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(createMemberCase.execute(member)));
    }

    @Operation(summary = "Update member", description = "Updates the data of an existing member")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Member successfully updated"),
        @ApiResponse(responseCode = "404", description = "Member not found")
    })
    @PutMapping("update/{memberId}")
    public ResponseEntity<MemberDTO> updateMember(
        @RequestBody MemberDTO memberDTO,
        @Parameter(description = "Member ID", required = true) @PathVariable UUID memberId){
        Member member = mapper.toEntity(memberDTO);

        return ResponseEntity.ok(mapper.toDto(updateMemberCase.execute(memberId, member)));
    }

    @Operation(summary = "Delete member", description = "Removes a member from the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Member successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Member not found")
    })
    @DeleteMapping("delete/{memberId}")
    public ResponseEntity<Void> deleteMember(
        @Parameter(description = "Member ID", required = true) @PathVariable UUID memberId){
        deleteMemberCase.execute(memberId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get member by ID", description = "Returns the details of a specific member")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Member found"),
        @ApiResponse(responseCode = "404", description = "Member not found")
    })
    @GetMapping("list/{memberId}")
    public ResponseEntity<MemberDTO> listMemberById(
        @Parameter(description = "Member ID", required = true) @PathVariable UUID memberId){
        return ResponseEntity.ok(mapper.toDto(listMemberCase.execute(memberId)));
    }

    @Operation(summary = "List all members", description = "Returns a list of all registered members")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List returned successfully")
    })
    @GetMapping("listAll")
    public ResponseEntity<List<MemberDTO>> listAllMembers(){
        return ResponseEntity.ok(listAllMembersCase.execute()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
    }

}
