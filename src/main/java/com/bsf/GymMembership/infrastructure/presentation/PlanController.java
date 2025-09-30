package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.usecases.plan.*;
import com.bsf.GymMembership.infrastructure.exception.NotFoundException;
import com.bsf.GymMembership.infrastructure.persistence.dto.PlanDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.PlanDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("plan")
@Tag(name = "Plans", description = "Operations related to gym membership plans")
public class PlanController {
    private final CreatePlanCase createPlanCase;
    private final DeletePlanCase deletePlanCase;
    private final ListPlanCase listPlanCase;
    private final ListByPlanCase listByPlanCase;
    private final PlanDtoMapper planDtoMapper;
    private final UpdatePlanCase updatePlanCase;

    public PlanController(CreatePlanCase createPlanCase, DeletePlanCase deletePlanCase, ListPlanCase listPlanCase, ListByPlanCase listByPlanCase, PlanDtoMapper planDtoMapper, UpdatePlanCase updatePlanCase) {
        this.createPlanCase = createPlanCase;
        this.deletePlanCase = deletePlanCase;
        this.listPlanCase = listPlanCase;
        this.listByPlanCase = listByPlanCase;
        this.planDtoMapper = planDtoMapper;
        this.updatePlanCase = updatePlanCase;
    }

    @Operation(summary = "Create a new plan", description = "Adds a new membership plan to the gym")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Plan successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @PostMapping("create")
    public ResponseEntity<PlanDTO> createPlan(@RequestBody PlanDTO planDTO){
        Plan newPlan = createPlanCase.execute(planDtoMapper.toEntity(planDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(planDtoMapper.toDto(newPlan));
    }

    @Operation(summary = "List all plans", description = "Returns a list of all registered plans")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List returned successfully")
    })
    @GetMapping("listAll")
    public ResponseEntity<List<PlanDTO>>listAllPlan(){
        return ResponseEntity.ok(listPlanCase.execute()
                .stream()
                .map(planDtoMapper::toDto)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Get plan by name", description = "Returns the details of a specific plan by its name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Plan found"),
        @ApiResponse(responseCode = "404", description = "Plan not found")
    })
    @GetMapping("listByPlan/{plan}")
    public ResponseEntity<PlanDTO> listByPlan(
        @Parameter(description = "Plan name", required = true) @PathVariable String plan){
        return  ResponseEntity.ok(listByPlanCase.execute(plan)
                .map(planDtoMapper::toDto)
                .orElseThrow((()-> new NotFoundException("Plan with name: "+ plan +" not found"))));
    }

    @Operation(summary = "Update plan", description = "Updates the data of an existing plan")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Plan successfully updated"),
        @ApiResponse(responseCode = "404", description = "Plan not found")
    })
    @PutMapping("update/{planId}")
    public ResponseEntity<PlanDTO> updatePlan(
        @RequestBody PlanDTO planDTO,
        @Parameter(description = "Plan ID", required = true) @PathVariable UUID planId){
        Plan plan = updatePlanCase.execute(planId, planDtoMapper.toEntity(planDTO));
        return ResponseEntity.ok(planDtoMapper.toDto(plan));
    }

    @Operation(summary = "Delete plan", description = "Removes a plan from the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Plan successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Plan not found")
    })
    @DeleteMapping("delete/{planId}")
    public ResponseEntity<Void> deletePlan(
        @Parameter(description = "Plan ID", required = true) @PathVariable UUID planId){
        deletePlanCase.execute(planId);
        return ResponseEntity.noContent().build();
    }

}
