package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.entity.GymClass;
import com.bsf.GymMembership.core.usecases.gymClass.CreateClassCase;
import com.bsf.GymMembership.infrastructure.persistence.dto.ClassDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.ClassDtoMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("class")
public class ClassController {
    private final CreateClassCase createClassCase;
    private final ClassDtoMapper classDtoMapper;

    public ClassController(CreateClassCase createClassCase, ClassDtoMapper classDtoMapper) {
        this.createClassCase = createClassCase;
        this.classDtoMapper = classDtoMapper;
    }

    @PostMapping("create")
    public ClassDTO createClass(@RequestBody ClassDTO classDTO){
        return classDtoMapper.toDto(createClassCase.execute(classDtoMapper.toEntity(classDTO)));
    }
}
