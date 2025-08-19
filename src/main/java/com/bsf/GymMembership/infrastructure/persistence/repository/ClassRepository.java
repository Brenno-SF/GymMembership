package com.bsf.GymMembership.infrastructure.persistence.repository;

import com.bsf.GymMembership.infrastructure.persistence.entitiy.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassEntity, String> {
}
