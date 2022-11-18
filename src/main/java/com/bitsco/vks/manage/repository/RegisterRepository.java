package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RegisterRepository extends JpaRepository<Register, String> {
    Optional<Register> findFirstByCaseCodeAndStage(String caseCode, String stage);
}
