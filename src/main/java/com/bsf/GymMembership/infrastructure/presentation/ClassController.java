package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.entity.GymClass;
import com.bsf.GymMembership.core.usecases.gymClass.*;
import com.bsf.GymMembership.infrastructure.exception.NotFoundException;
import com.bsf.GymMembership.infrastructure.persistence.dto.ClassDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.ClassDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("class")
public class ClassController {
    private final CreateClassCase createClassCase;
    private final UpdateClassCase updateClassCase;
    private final DeleteClassCase deleteClassCase;
    private final ListAllClassesCase listAllClassesCase;
    private final ListClassCase listClassCase;
    private final ClassDtoMapper classDtoMapper;

    public ClassController(CreateClassCase createClassCase, UpdateClassCase updateClassCase, DeleteClassCase deleteClassCase, ListAllClassesCase listAllClassesCase, ListClassCase listClassCase, ClassDtoMapper classDtoMapper) {
        this.createClassCase = createClassCase;
        this.updateClassCase = updateClassCase;
        this.deleteClassCase = deleteClassCase;
        this.listAllClassesCase = listAllClassesCase;
        this.listClassCase = listClassCase;
        this.classDtoMapper = classDtoMapper;
    }

    @PostMapping("create")
    public ResponseEntity<ClassDTO> createClass(@RequestBody ClassDTO classDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(classDtoMapper.toDto(createClassCase.execute(classDtoMapper.toEntity(classDTO))));
    }
    @GetMapping("listAll")
    public List<ClassDTO> listAllClasses(){
        return listAllClassesCase.execute()
                .stream()
                .map(classDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    @GetMapping("list/{classId}")
    public ResponseEntity<ClassDTO> listAllClasses(@PathVariable UUID classId){
        return ResponseEntity.ok(classDtoMapper.toDto(listClassCase.execute(classId)
                .orElseThrow(() -> new NotFoundException("Class Not Found"))));
    }
    @PutMapping("update/{classId}")
    public ResponseEntity<ClassDTO> updateClass(@RequestBody ClassDTO classDTO, @PathVariable UUID classId){
        return ResponseEntity.ok(classDtoMapper.toDto(updateClassCase.execute(classId, classDtoMapper.toEntity(classDTO))));
    }
    @DeleteMapping("delete/{classId}")
    public ResponseEntity<Void> deleteClass( @PathVariable UUID classId){
        deleteClassCase.execute(classId);
        return ResponseEntity.noContent()
                .build();
    }
}
