package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.usecases.presence.CreatePresenceCase;
import com.bsf.GymMembership.core.usecases.presence.ListPresenceByClassId;
import com.bsf.GymMembership.core.usecases.presence.ListPresenceById;
import com.bsf.GymMembership.core.usecases.presence.ListPresenceByMemberId;
import com.bsf.GymMembership.infrastructure.exception.NotFoundException;
import com.bsf.GymMembership.infrastructure.persistence.dto.PresenceDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.PresenceDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("presence")
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

    @PostMapping("/create")
    public PresenceDTO createPresence(@RequestBody PresenceDTO presenceDTO){
        return presenceDtoMapper.toDto(
                presenceCase.execute(presenceDtoMapper.toEntity(presenceDTO))
        );
    }

    @GetMapping("/list/{presenceId}")
    public PresenceDTO listPresence(@PathVariable UUID presenceId){
        return presenceDtoMapper.toDto(
                listPresenceById.execute(presenceId).orElseThrow(()-> new NotFoundException("Presence Not found"))
        );
    }
    @GetMapping("/listByMember/{memberId}")
    public List<PresenceDTO> listPresenceByMember(@PathVariable UUID memberId){
        return listPresenceByMemberId.execute(memberId)
                .stream()
                .map(presenceDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/listByClass/{classId}")
    public List<PresenceDTO> listPresenceByClass(@PathVariable UUID classId){
        return listPresenceByClassId.execute(classId)
                .stream()
                .map(presenceDtoMapper::toDto)
                .collect(Collectors.toList());
    }

}
