package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Spc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SpcRepository extends JpaRepository<Spc, String> {
    @Query(value = "SELECT a FROM Spc a WHERE 1 = 1 "
            + "AND (:spcId IS NULL OR a.spcId = :spcId) "
            + "AND (:name IS NULL OR upper(a.name) LIKE upper(:name) ) "
            + "AND (:address IS NULL OR a.address LIKE :address) "
    )
    List<Spc> getList(
            @Param("spcId") String spcId,
            @Param("name") String name,
            @Param("address") String address
    );
}
