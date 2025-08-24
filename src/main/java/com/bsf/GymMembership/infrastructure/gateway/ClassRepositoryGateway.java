package com.bsf.GymMembership.infrastructure.gateway;

import com.bsf.GymMembership.core.entity.GymClass;
import com.bsf.GymMembership.core.gateway.ClassGateway;
import com.bsf.GymMembership.infrastructure.exception.NotFoundException;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.ClassEntity;
import com.bsf.GymMembership.infrastructure.persistence.mapper.ClassEntityMapper;
import com.bsf.GymMembership.infrastructure.persistence.repository.ClassRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return classEntityMapper.toDomain(classRepository.save(classEntityMapper.toEntity(gymClass))) ;
    }

    @Override
    public List<GymClass> listAll() {
        return classRepository.findAll()
                .stream()
                .map(classEntityMapper::toDomain)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<GymClass> listById(UUID classId) {

        return classRepository.findById(classId)
                .map(classEntityMapper::toDomain);
    }
}
