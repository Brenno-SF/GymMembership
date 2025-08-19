package com.bsf.GymMembership.infrastructure.persistence.entitiy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "presence_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PresenceEntity {

    @Id
    @Column(name = "presence_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID presenceId;

    @ManyToOne
    @JoinColumn(name = "member_id_fk", nullable = false)
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "class_id_fk", nullable = false)
    private ClassEntity gymClass;

    @Column(name = "presence", nullable = false)
    private Boolean presence;

    @Column(name = "register", nullable = false)
    private LocalDate register;

}