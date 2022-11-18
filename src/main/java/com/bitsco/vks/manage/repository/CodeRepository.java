package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CodeRepository extends JpaRepository<Code, String> {
    @Query(value = "SELECT a FROM Code a WHERE 1 = 1 "
            + "AND (:status IS NULL OR a.status = :status) "
            + "AND (a.codeType = 'BLHS') "
            + "ORDER BY a.codeYear desc"
    )
    List<Code> getList(
            @Param("status") String status
    );
}
