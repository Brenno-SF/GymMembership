package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.usecases.presence.CreatePresenceCase;
import com.bsf.GymMembership.core.usecases.presence.ListPresenceByClassId;
import com.bsf.GymMembership.core.usecases.presence.ListPresenceById;
import com.bsf.GymMembership.core.usecases.presence.ListPresenceByMemberId;
import com.bsf.GymMembership.infrastructure.exception.NotFoundException;
import com.bsf.GymMembership.infrastructure.persistence.dto.PresenceDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.PresenceDtoMapper;
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
@RequestMapping("presence")
@Tag(name = "Presences", description = "Operations related to class presences")
public class PresenceController {
    private final CreatePresenceCase presenceCase;
    private final PresenceDtoMapper presenceDtoMapper;
    private final ListPresenceById listPresenceById;
    private final ListPresenceByMemberId listPresenceByMemberId;
    private final ListPresenceByClassId listPresenceByClassId;

    public PresenceController(CreatePresenceCase presenceCase, PresenceDtoMapper presenceDtoMapper, ListPresenceById listPresenceById, ListPresenceByMemberId listPresenceByMemberId, ListPresenceByClassId listPresenceByClassId) {
        this.presenceCase = presenceCase;
        this.presenceDtoMapper = presenceDtoMapper;
        this.listPresenceById = listPresenceById;
        this.listPresenceByMemberId = listPresenceByMemberId;
        this.listPresenceByClassId = listPresenceByClassId;
    }

    @Operation(summary = "Create a new presence", description = "Adds a new presence record for a class")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Presence successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @PostMapping("/create")
    public ResponseEntity<PresenceDTO> createPresence(@RequestBody PresenceDTO presenceDTO){
        return ResponseEntity.
                status(HttpStatus.CREATED).body(presenceDtoMapper.toDto(
                        presenceCase.execute(presenceDtoMapper.toEntity(presenceDTO))));
    }

    @Operation(summary = "Get presence by ID", description = "Returns the details of a specific presence")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Presence found"),
        @ApiResponse(responseCode = "404", description = "Presence not found")
    })
    @GetMapping("/list/{presenceId}")
    public ResponseEntity<PresenceDTO> listPresence(
        @Parameter(description = "Presence ID", required = true) @PathVariable UUID presenceId){
        return ResponseEntity.ok(presenceDtoMapper.toDto(
                listPresenceById.execute(presenceId).orElseThrow(()-> new NotFoundException("Presence Not found")))
        );
    }
    @Operation(summary = "List presences by member", description = "Returns all presences for a specific member")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List returned successfully")
    })
    @GetMapping("/listByMember/{memberId}")
    public ResponseEntity<List<PresenceDTO>> listPresenceByMember(
        @Parameter(description = "Member ID", required = true) @PathVariable UUID memberId){
        return ResponseEntity.ok(listPresenceByMemberId.execute(memberId)
                .stream()
                .map(presenceDtoMapper::toDto)
                .collect(Collectors.toList()));
    }
    @Operation(summary = "List presences by class", description = "Returns all presences for a specific class")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List returned successfully")
    })
    @GetMapping("/listByClass/{classId}")
    public ResponseEntity<List<PresenceDTO>> listPresenceByClass(
        @Parameter(description = "Class ID", required = true) @PathVariable UUID classId){
        return ResponseEntity.ok(listPresenceByClassId.execute(classId)
                .stream()
                .map(presenceDtoMapper::toDto)
                .collect(Collectors.toList()));
    }

}
