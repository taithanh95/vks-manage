package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.GroupLaw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface GroupLawRepository extends JpaRepository<GroupLaw, String> {
    @Query(value = "SELECT a FROM GroupLaw a WHERE 1 = 1 "
            + "AND (:status IS NULL OR a.status = :status) "
    )
    List<GroupLaw> getList(
            @Param("status") String status
    );
}
