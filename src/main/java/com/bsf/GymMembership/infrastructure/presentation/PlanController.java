package com.bsf.GymMembership.infrastructure.presentation;

import com.bsf.GymMembership.core.entity.Plan;
import com.bsf.GymMembership.core.usecases.CreatePlanCase;
import com.bsf.GymMembership.infrastructure.persistence.dto.PlanDTO;
import com.bsf.GymMembership.infrastructure.persistence.mapper.PlanDtoMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("plan")
public class PlanController {
    private final CreatePlanCase createPlanCase;
    private final PlanDtoMapper planDtoMapper;

    public PlanController(CreatePlanCase createPlanCase, PlanDtoMapper planDtoMapper) {
        this.createPlanCase = createPlanCase;
        this.planDtoMapper = planDtoMapper;
    }

    @PostMapping("create")
    public PlanDTO createPlan(@RequestBody PlanDTO planDTO){
        Plan newPlan = createPlanCase.execute(planDtoMapper.toEntity(planDTO));
        return planDtoMapper.toDto(newPlan);
    }
}
