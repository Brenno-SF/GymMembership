package com.bsf.GymMembership.infrastructure.persistence.mapper;


import com.bsf.GymMembership.core.entity.Class;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.ClassEntity;
import org.springframework.stereotype.Component;

@Component
public class ClassEntityMapper {

    public ClassEntity toEntity(Class clazz) {
        return new ClassEntity(
                clazz.classId(),
                clazz.name(),
                clazz.description(),
                clazz.dateHour()
        );
    }

    public Class toDomain(ClassEntity entity) {
        return new Class(
                entity.getClassId(),
                entity.getNameClass(),
                entity.getDescriptionClass(),
                entity.getDateHour()
        );
    }
}