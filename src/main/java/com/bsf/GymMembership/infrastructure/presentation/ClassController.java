package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.entity.GymClass;
import com.bsf.GymMembership.core.usecases.gymClass.*;
import com.bsf.GymMembership.infrastructure.exception.NotFoundException;
import com.bsf.GymMembership.infrastructure.persistence.dto.ClassDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.ClassDtoMapper;
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
@RequestMapping("class")
@Tag(name = "Classes", description = "Operations related to gym classes")
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

    @Operation(summary = "Create a new class", description = "Adds a new class to the gym")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Class successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @PostMapping("create")
    public ResponseEntity<ClassDTO> createClass(@RequestBody ClassDTO classDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(classDtoMapper.toDto(createClassCase.execute(classDtoMapper.toEntity(classDTO))));
    }
    @Operation(summary = "List all classes", description = "Returns a list of all registered classes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List returned successfully")
    })
    @GetMapping("listAll")
    public List<ClassDTO> listAllClasses(){
        return listAllClassesCase.execute()
                .stream()
                .map(classDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    @Operation(summary = "Get class by ID", description = "Returns the details of a specific class")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Class found"),
        @ApiResponse(responseCode = "404", description = "Class not found")
    })
    @GetMapping("list/{classId}")
    public ResponseEntity<ClassDTO> listAllClasses(
        @Parameter(description = "Class ID", required = true) @PathVariable UUID classId){
        return ResponseEntity.ok(classDtoMapper.toDto(listClassCase.execute(classId)
                .orElseThrow(() -> new NotFoundException("Class Not Found"))));
    }
    @Operation(summary = "Update class", description = "Updates the data of an existing class")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Class successfully updated"),
        @ApiResponse(responseCode = "404", description = "Class not found")
    })
    @PutMapping("update/{classId}")
    public ResponseEntity<ClassDTO> updateClass(
        @RequestBody ClassDTO classDTO,
        @Parameter(description = "Class ID", required = true) @PathVariable UUID classId){
        return ResponseEntity.ok(classDtoMapper.toDto(updateClassCase.execute(classId, classDtoMapper.toEntity(classDTO))));
    }
    @Operation(summary = "Delete class", description = "Removes a class from the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Class successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Class not found")
    })
    @DeleteMapping("delete/{classId}")
    public ResponseEntity<Void> deleteClass(
        @Parameter(description = "Class ID", required = true) @PathVariable UUID classId){
        deleteClassCase.execute(classId);
        return ResponseEntity.noContent()
                .build();
    }
}
