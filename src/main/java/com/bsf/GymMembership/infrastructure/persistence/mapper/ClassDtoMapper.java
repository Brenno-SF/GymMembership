package com.bsf.GymMembership.infrastructure.persistence.mapper;
import com.bsf.GymMembership.core.entity.Class;
import com.bsf.GymMembership.infrastructure.persistence.dto.ClassDTO;
import org.springframework.stereotype.Component;

@Component
public class ClassDtoMapper {
    public static Class toEntity(ClassDTO dto) {
        return new Class(
                dto.classId(),
                dto.name(),
                dto.description(),
                dto.dateHour()
        );
    }

    public static ClassDTO toDto(Class entity) {
        return new ClassDTO(
                entity.classId(),
                entity.name(),
                entity.description(),
                entity.dateHour()
        );
    }
}
