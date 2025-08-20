package com.bsf.GymMembership.infrastructure.gateway;

import com.bsf.GymMembership.core.gateway.ClassGateway;
import com.bsf.GymMembership.infrastructure.persistence.repository.ClassRepository;
import org.springframework.stereotype.Component;

@Component
public class ClassRepositoryGateway implements ClassGateway {
    private final ClassRepository classRepository;

    public ClassRepositoryGateway(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }


}
