package com.bsf.GymMembership.infrastructure.persistence.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "plan_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanEntity {

    @Id
    @Column(name = "plan_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID planId;

    @Column(name = "name_plan", nullable = false, length = 255)
    private String namePlan;

    @Column(name = "description_plan", nullable = false, columnDefinition = "TEXT")
    private String descriptionPlan;

    @Column(name = "max_capacity", nullable = false)
    private Integer maxCapacity;

    @Column(name = "duration_month", nullable = false)
    private Integer durationMonth;

}