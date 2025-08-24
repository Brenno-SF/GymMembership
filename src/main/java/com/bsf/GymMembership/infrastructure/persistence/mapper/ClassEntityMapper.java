package com.bsf.GymMembership.infrastructure.persistence.mapper;


import com.bsf.GymMembership.core.entity.GymClass;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.ClassEntity;
import org.springframework.stereotype.Component;

@Component
public class ClassEntityMapper {

    public ClassEntity toEntity(GymClass clazz) {
        return new ClassEntity(
                clazz.classId(),
                clazz.name(),
                clazz.description(),
                clazz.dateHour()
        );
    }

    public GymClass toDomain(ClassEntity entity) {
        return new GymClass(
                entity.getClassId(),
                entity.getNameClass(),
                entity.getDescriptionClass(),
                entity.getDateHour()
        );
    }
}