package com.bsf.GymMembership.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "class_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntity {

    @Id
    @Column(name = "class_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID classId;

    @Column(name = "name_class", nullable = false, length = 255)
    private String nameClass;

    @Column(name = "description_class", nullable = false, columnDefinition = "TEXT")
    private String descriptionClass;

    @Column(name = "date_hour", nullable = false)
    private LocalDateTime dateHour;

    }