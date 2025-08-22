package com.bsf.GymMembership.infrastructure.persistence.repository;

import com.bsf.GymMembership.infrastructure.persistence.entitiy.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<MemberEntity, UUID> {
}
