package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Centences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CentencesRepository extends JpaRepository<Centences, String> {
    @Query(value = "SELECT a FROM Centences a WHERE 1 = 1 "
            + "AND (:status IS NULL OR a.status = :status) "
            + "AND (:caseCode IS NULL OR a.caseCode = :caseCode) "
    )
    List<Centences> getList(
            @Param("status") String status,
            @Param("caseCode") String caseCode
    );
}
