package com.bsf.GymMembership.infrastructure.persistence.mapper;
import com.bsf.GymMembership.core.entity.GymClass;
import com.bsf.GymMembership.infrastructure.persistence.dto.ClassDTO;
import org.springframework.stereotype.Component;

@Component
public class ClassDtoMapper {
    public GymClass toEntity(ClassDTO dto) {
        return new GymClass(
                dto.classId(),
                dto.name(),
                dto.description(),
                dto.dateHour()
        );
    }

    public ClassDTO toDto(GymClass entity) {
        return new ClassDTO(
                entity.classId(),
                entity.name(),
                entity.description(),
                entity.dateHour()
        );
    }
}
