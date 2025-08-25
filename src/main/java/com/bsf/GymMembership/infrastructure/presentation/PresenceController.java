package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.usecases.presence.CreatePresenceCase;
import com.bsf.GymMembership.infrastructure.persistence.dto.PresenceDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.PresenceDtoMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("presence")
public class PresenceController {
    private final CreatePresenceCase presenceCase;
    private final PresenceDtoMapper presenceDtoMapper;

    public PresenceController(CreatePresenceCase presenceCase, PresenceDtoMapper presenceDtoMapper) {
        this.presenceCase = presenceCase;
        this.presenceDtoMapper = presenceDtoMapper;
    }

    @PostMapping("/create")
    public PresenceDTO createPresence(@RequestBody PresenceDTO presenceDTO){

        return presenceDtoMapper.toDto(
                presenceCase.execute(presenceDtoMapper.toEntity(presenceDTO))
        );

    }
}
