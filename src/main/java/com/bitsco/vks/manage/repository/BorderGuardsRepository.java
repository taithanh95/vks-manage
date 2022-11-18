package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.BorderGuards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BorderGuardsRepository extends JpaRepository<BorderGuards, String> {
    @Query(value = "SELECT a FROM BorderGuards a WHERE 1 = 1 "
            + "AND (:borguaId IS NULL OR a.borguaId = :borguaId) "
            + "AND (:borguaName IS NULL OR upper(a.borguaName) LIKE upper(:borguaName) ) "
            + "AND (:address IS NULL OR a.address LIKE :address) "
            + "AND (:status IS NULL OR a.status = :status) "
    )
    List<BorderGuards> getList(
            @Param("borguaId") String borguaId,
            @Param("borguaName") String borguaName,
            @Param("address") String address,
            @Param("status") String status
    );
}
