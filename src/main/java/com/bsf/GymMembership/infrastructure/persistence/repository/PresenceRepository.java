package com.bsf.GymMembership.infrastructure.persistence.repository;

import com.bsf.GymMembership.infrastructure.persistence.entitiy.PresenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PresenceRepository extends JpaRepository<PresenceEntity, UUID> {
    public List<PresenceEntity> findAllByMemberId(UUID memberId);
}
