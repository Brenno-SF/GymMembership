package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.usecases.plan.CreatePlanCase;
import com.bsf.GymMembership.core.usecases.plan.ListByPlanCase;
import com.bsf.GymMembership.core.usecases.plan.ListPlanCase;
import com.bsf.GymMembership.infrastructure.exception.NotFoundException;
import com.bsf.GymMembership.infrastructure.persistence.dto.PlanDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.PlanDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("plan")
public class PlanController {
    private final CreatePlanCase createPlanCase;
    private final ListPlanCase listPlanCase;
    private final ListByPlanCase listByPlanCase;
    private final PlanDtoMapper planDtoMapper;

    public PlanController(CreatePlanCase createPlanCase, ListPlanCase listPlanCase, ListByPlanCase listByPlanCase, PlanDtoMapper planDtoMapper) {
        this.createPlanCase = createPlanCase;
        this.listPlanCase = listPlanCase;
        this.listByPlanCase = listByPlanCase;
        this.planDtoMapper = planDtoMapper;
    }

    @PostMapping("create")
    public PlanDTO createPlan(@RequestBody PlanDTO planDTO){
        Plan newPlan = createPlanCase.execute(planDtoMapper.toEntity(planDTO));
        return planDtoMapper.toDto(newPlan);
    }
    @GetMapping("listAll")
    public List<PlanDTO>listAllPlan(){
        return listPlanCase.execute()
                .stream()
                .map(planDtoMapper::toDto)
                .collect(Collectors.toList());
    }
    @GetMapping("listByPlan/{plan}")
    public PlanDTO listByPlan(@PathVariable String plan){
        return listByPlanCase.execute(plan)
                .map(planDtoMapper::toDto)
                .orElseThrow((()-> new NotFoundException("Plan with name: "+ plan +" not found")));
    }
}
