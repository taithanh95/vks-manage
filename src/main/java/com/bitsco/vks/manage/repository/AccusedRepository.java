package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Accused;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AccusedRepository extends JpaRepository<Accused, String> {
    @Query(value = "SELECT a FROM Accused a WHERE 1 = 1 "
            + "AND (:sppId IS NULL OR a.sppId = :sppId) "
            + "AND (:fullName IS NULL OR upper(a.fullName) LIKE upper(:fullName)) "
            + "AND (:caseCode IS NULL OR a.caseCode = :caseCode) "
            + "AND (:status IS NULL OR a.status = :status) "
    )
    List<Accused> getList(
            @Param("sppId") String sppId,
            @Param("fullName") String fullName,
            @Param("caseCode") String caseCode,
            @Param("status") String status
    );

    List<Accused> findByCaseCode(String caseCode);
}
