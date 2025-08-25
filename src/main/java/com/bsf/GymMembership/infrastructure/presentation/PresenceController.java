package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.usecases.presence.CreatePresenceCase;
import com.bsf.GymMembership.core.usecases.presence.ListPresenceById;
import com.bsf.GymMembership.infrastructure.exception.NotFoundException;
import com.bsf.GymMembership.infrastructure.persistence.dto.PresenceDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.PresenceDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("presence")
public class PresenceController {
    private final CreatePresenceCase presenceCase;
    private final PresenceDtoMapper presenceDtoMapper;
    private final ListPresenceById listPresenceById;

    public PresenceController(CreatePresenceCase presenceCase, PresenceDtoMapper presenceDtoMapper, ListPresenceById listPresenceById) {
        this.presenceCase = presenceCase;
        this.presenceDtoMapper = presenceDtoMapper;
        this.listPresenceById = listPresenceById;
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
}
