package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Customs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CustomsRepository extends JpaRepository<Customs, String> {
    @Query(value = "SELECT a FROM Customs a WHERE 1 = 1 "
            + "AND (:customId IS NULL OR a.customId = :customId) "
            + "AND (:customName IS NULL OR upper(a.customName) LIKE upper(:customName) ) "
            + "AND (:address IS NULL OR a.address LIKE :address) "
            + "AND (:status IS NULL OR a.status = :status) "
    )
    List<Customs> getList(
            @Param("customId") String customId,
            @Param("customName") String customName,
            @Param("address") String address,
            @Param("status") String status
    );
}
