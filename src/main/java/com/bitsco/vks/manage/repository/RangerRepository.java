package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Ranger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface RangerRepository extends JpaRepository<Ranger, String> {
    @Query(value = "SELECT a FROM Ranger a WHERE 1 = 1 "
            + "AND (:rangId IS NULL OR a.rangId = :rangId) "
            + "AND (:rangName IS NULL OR upper(a.rangName) LIKE upper(:rangName) ) "
            + "AND (:address IS NULL OR a.address LIKE :address) "
            + "AND (:status IS NULL OR a.status = :status) "
    )
    List<Ranger> getList(
            @Param("rangId") String rangId,
            @Param("rangName") String rangName,
            @Param("address") String address,
            @Param("status") String status
    );
}
