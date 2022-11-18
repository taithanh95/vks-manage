package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Army;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ArmyRepository extends JpaRepository<Army, String> {
    @Query(value = "SELECT a FROM Army a WHERE 1 = 1 "
            + "AND (:armyId IS NULL OR a.armyId = :armyId) "
            + "AND (:armyName IS NULL OR upper(a.armyName) LIKE upper(:armyName) ) "
            + "AND (:address IS NULL OR a.address LIKE :address) "
            + "AND (:status IS NULL OR a.status = :status) "
    )
    List<Army> getList(
            @Param("armyId") String armyId,
            @Param("armyName") String armyName,
            @Param("address") String address,
            @Param("status") String status
    );
}
