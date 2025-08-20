package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.usecases.plan.CreatePlanCase;
import com.bsf.GymMembership.core.usecases.plan.ListPlanCase;
import com.bsf.GymMembership.infrastructure.persistence.dto.PlanDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.PlanDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("plan")
public class PlanController {
    private final CreatePlanCase createPlanCase;
    private final ListPlanCase listPlanCase;
    private final PlanDtoMapper planDtoMapper;

    public PlanController(CreatePlanCase createPlanCase, ListPlanCase listPlanCase, PlanDtoMapper planDtoMapper) {
        this.createPlanCase = createPlanCase;
        this.listPlanCase = listPlanCase;
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
}
