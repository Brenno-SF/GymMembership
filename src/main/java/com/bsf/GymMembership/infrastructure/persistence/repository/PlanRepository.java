package com.bsf.GymMembership.infrastructure.persistence.repository;

import com.bsf.GymMembership.infrastructure.persistence.entitiy.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<PlanEntity, String> {
}
