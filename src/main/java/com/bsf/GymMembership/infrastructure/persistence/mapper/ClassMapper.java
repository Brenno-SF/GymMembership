package com.bsf.GymMembership.infrastructure.persistence.mapper;
import com.bsf.GymMembership.infrastructure.persistence.entitiy.ClassEntity;
import com.bsf.GymMembership.infrastructure.persistence.request.ClassRequestDTO;
import com.bsf.GymMembership.infrastructure.persistence.response.ClassResponseDTO;

public class ClassMapper {
    public static ClassEntity toEntity(ClassRequestDTO dto) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setNameClass(dto.name());
        classEntity.setDescriptionClass(dto.description());
        classEntity.setDateHour(dto.dateHour());
        return classEntity;
    }

    public static ClassResponseDTO toDto(ClassEntity entity) {
        return new ClassResponseDTO(
                entity.getClassId(),
                entity.getNameClass(),
                entity.getDescriptionClass(),
                entity.getDateHour()
        );
    }
}