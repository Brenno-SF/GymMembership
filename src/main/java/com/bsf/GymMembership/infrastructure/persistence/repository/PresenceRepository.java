package com.bsf.GymMembership.infrastructure.persistence.repository;

import com.bsf.GymMembership.infrastructure.persistence.entitiy.PresenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresenceRepository extends JpaRepository<PresenceEntity, String > {
}
