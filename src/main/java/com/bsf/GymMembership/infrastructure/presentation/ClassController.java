package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.entity.GymClass;
import com.bsf.GymMembership.core.usecases.gymClass.CreateClassCase;
import com.bsf.GymMembership.core.usecases.gymClass.ListAllClassesCase;
import com.bsf.GymMembership.infrastructure.persistence.dto.ClassDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.ClassDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("class")
public class ClassController {
    private final CreateClassCase createClassCase;
    private final ListAllClassesCase listAllClassesCase;
    private final ClassDtoMapper classDtoMapper;

    public ClassController(CreateClassCase createClassCase, ListAllClassesCase listAllClassesCase, ClassDtoMapper classDtoMapper) {
        this.createClassCase = createClassCase;
        this.listAllClassesCase = listAllClassesCase;
        this.classDtoMapper = classDtoMapper;
    }

    @PostMapping("create")
    public ClassDTO createClass(@RequestBody ClassDTO classDTO){
        return classDtoMapper.toDto(createClassCase.execute(classDtoMapper.toEntity(classDTO)));
    }
    @GetMapping("listAll")
    public List<ClassDTO> listAllClasses(){
        return listAllClassesCase.execute()
                .stream()
                .map(classDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
