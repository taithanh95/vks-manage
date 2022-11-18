package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Police;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PoliceRepository extends JpaRepository<Police, String> {
    @Query(value = "SELECT a FROM Police a WHERE 1 = 1 "
            + "AND (:policeId IS NULL OR a.policeId = :policeId) "
            + "AND (:name IS NULL OR UPPER(a.name) LIKE UPPER(:name) ) "
            + "AND (:address IS NULL OR a.address LIKE :address) "
            + "AND (:status IS NULL OR a.status = :status) "
    )
    List<Police> getList(
            @Param("policeId") String policeId,
            @Param("name") String name,
            @Param("address") String address,
            @Param("status") String status
    );
}
