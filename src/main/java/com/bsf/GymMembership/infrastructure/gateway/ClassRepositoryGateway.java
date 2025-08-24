package com.bsf.GymMembership.infrastructure.gateway;

import com.bsf.GymMembership.core.entity.GymClass;
import com.bsf.GymMembership.core.gateway.ClassGateway;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.ClassEntity;
import com.bsf.GymMembership.infrastructure.persistence.mapper.ClassEntityMapper;
import com.bsf.GymMembership.infrastructure.persistence.repository.ClassRepository;
import org.springframework.stereotype.Component;

@Component
public class ClassRepositoryGateway implements ClassGateway {
    private final ClassRepository classRepository;
    private final ClassEntityMapper classEntityMapper;

    public ClassRepositoryGateway(ClassRepository classRepository, ClassEntityMapper classEntityMapper) {
        this.classRepository = classRepository;
        this.classEntityMapper = classEntityMapper;
    }

    @Override
    public GymClass createClass(GymClass gymClass) {
        ClassEntity entity = classRepository.save(classEntityMapper.toEntity(gymClass));
        return classEntityMapper.toDomain(entity) ;
    }
}
